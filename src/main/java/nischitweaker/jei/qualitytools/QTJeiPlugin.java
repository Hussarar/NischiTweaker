package nischitweaker.jei.qualitytools;

import com.google.gson.*;
import com.tmtravlr.qualitytools.config.ConfigLoader;
import com.tmtravlr.qualitytools.config.CustomMaterial;
import com.tmtravlr.qualitytools.config.QualityType;
import com.tmtravlr.qualitytools.reforging.BlockReforgingStation;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.*;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import nischitweaker.CompatUtil;
import nischitweaker.Tags;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Not annotated with @JEIPlugin, gets called by nischitweaker.jei.JeiPlugin only if Quality Tools is loaded
public class QTJeiPlugin implements IModPlugin {

    private static CustomMaterial universalMaterial = null;
    private static final Map<Item, List<ItemStack>> customMaterials = new HashMap<>();
    private static final Map<Class<?>, Class<?>> repairableDeclarers = new HashMap<>();
    private static List<ItemStack> allMaterialStacks = null;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new QTRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        //Adding the recipes
        registry.addRecipes(getRecipes(registry.getJeiHelpers()), QTRecipeCategory.UID);

        //See Reforging Station recipes when right-clicking the Reforging Station block in JEI
        registry.addRecipeCatalyst(new ItemStack(BlockReforgingStation.INSTANCE), QTRecipeCategory.UID);
    }

    private List<QTRecipeWrapper> getRecipes(IJeiHelpers helpers) {
        long startTime = System.currentTimeMillis();

        //Load configs early
        ConfigLoader.reloadConfigs();
        universalMaterial = null;
        loadCustomReforgingMaterialsEarly();

        List<QTRecipeWrapper> recipeList = new ArrayList<>();
        for (Item gearItem : ForgeRegistries.ITEMS) {
            ItemStack gearStack = new ItemStack(gearItem); //this approach does not allow metadata on the gear stacks, but that doesn't make sense anyway

            //Is able to get quality
            if (gearStack.isEmpty() || !canGetQuality(gearStack)) continue;
            //Is not hidden in JEI, more expensive so checked after the quality check
            if (helpers.getIngredientBlacklist().isIngredientBlacklisted(gearStack)) continue;

            List<ItemStack> materials = new ArrayList<>(getRepairMaterials(gearItem, gearStack));

            //Add custom config entries
            if (customMaterials.containsKey(gearItem))
                materials.addAll(customMaterials.get(gearItem));

            if (!materials.isEmpty())
                recipeList.add(new QTRecipeWrapper(gearStack, materials));
        }

        if (universalMaterial != null)
            recipeList.forEach(r -> r.addMaterial(universalMaterial));

        //Clear caches, no need for them after startup
        QTRecipeWrapper.clearCache();
        customMaterials.clear();
        repairableDeclarers.clear();
        allMaterialStacks = null;

        Tags.LOGGER.info("Created {} Quality Tools reforging recipes for JEI in {}ms", recipeList.size(), System.currentTimeMillis() - startTime);
        return recipeList;
    }

    //Same as QualityToolsHelper.generateQualityTag without actually rolling a quality and writing it to the stack
    private static boolean canGetQuality(ItemStack stack) {
        if (stack.getItem().getItemStackLimit(stack) != 1 && !ConfigLoader.allowStackableItems) return false;
        for (QualityType qualityType : ConfigLoader.qualityTypes.values())
            if (qualityType != null && qualityType.itemMatches(stack))
                return true;
        return false;
    }

    //Mirrors the getIsRepairable part of QualityToolsHelper.canReforgeWith
    private static List<ItemStack> getRepairMaterials(Item gearItem, ItemStack gearStack) {
        if (!ConfigLoader.useRepairItem) return Collections.emptyList();

        //getIsRepairable only returns a single answer per tool+material pair, so ideally we would need to test every registered item
        //Instead check which class implements getIsRepairable. For the vanilla implementations we know the result without testing
        Class<?> declarer = repairableDeclarers.computeIfAbsent(gearItem.getClass(), QTJeiPlugin::getRepairableDeclarer);
        boolean isVanillaBody = declarer == Item.class || declarer == ItemSword.class || declarer == ItemTool.class || declarer == ItemArmor.class;

        //MaterialTweaker injects into these vanilla bodies, so we need to ask it directly
        //ItemShield and ItemElytra (also injected) and all other implementations are handled by testing every item
        if (isVanillaBody && Loader.isModLoaded("materialtweaker")) {
            ItemStack overrideStack = MaterialTweakerCompat.getOverrideRepairStack(gearItem);
            if (overrideStack != null) return Collections.singletonList(overrideStack);
        }

        //Item.getIsRepairable always returns false
        if (declarer == Item.class) return Collections.emptyList();

        //ItemSword, ItemTool and ItemArmor only accept their material's repair item (wildcard meta gets expanded by JEI)
        try {
            ItemStack repairStack = null;
            if (declarer == ItemSword.class)
                repairStack = Item.ToolMaterial.valueOf(((ItemSword) gearItem).getToolMaterialName()).getRepairItemStack();
            else if (declarer == ItemTool.class)
                repairStack = Item.ToolMaterial.valueOf(((ItemTool) gearItem).getToolMaterialName()).getRepairItemStack();
            else if (declarer == ItemArmor.class)
                repairStack = ((ItemArmor) gearItem).getArmorMaterial().getRepairItemStack();
            if (repairStack != null)
                return repairStack.isEmpty() ? Collections.emptyList() : Collections.singletonList(repairStack);
        } catch (IllegalArgumentException ignored) {} //Material name not found in ToolMaterial enum, fall back to testing every item

        //Custom implementation, go through all registered items and check if they can reforge the gear item
        if (allMaterialStacks == null) {
            allMaterialStacks = new ArrayList<>();
            for (Item materialItem : ForgeRegistries.ITEMS) {
                ItemStack materialStack = new ItemStack(materialItem);
                if (!materialStack.isEmpty()) allMaterialStacks.add(materialStack);
            }
        }
        List<ItemStack> materials = new ArrayList<>();
        for (ItemStack materialStack : allMaterialStacks)
            if (gearItem.getIsRepairable(gearStack, materialStack))
                materials.add(materialStack);
        return materials;
    }

    //Returns the class that implements getIsRepairable for the given item class, or null if it can't be found
    private static Class<?> getRepairableDeclarer(Class<?> itemClass) {
        try {
            return itemClass.getMethod(CompatUtil.isDev() ? "getIsRepairable" : "func_82789_a", ItemStack.class, ItemStack.class).getDeclaringClass();
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static void loadCustomReforgingMaterialsEarly() {
        if (ConfigLoader.configFolder == null) return;
        File customReforgingFile = new File(ConfigLoader.configFolder, "reforging materials.json");

        try {
            InputStreamReader reader = new InputStreamReader(Files.newInputStream(customReforgingFile.toPath()));
            Gson gson = new GsonBuilder().registerTypeAdapter(CustomMaterial.class, new CustomMaterial.Serializer()).create();

            for (JsonElement jsonElement : new JsonParser().parse(reader).getAsJsonArray()) {
                JsonObject json = JsonUtils.getJsonObject(jsonElement, "");

                CustomMaterial material = gson.fromJson(JsonUtils.getJsonObject(json, "material"), CustomMaterial.class);
                if (material == null) continue;

                //Get ItemStacks matching the material settings, using a cache for faster lookup
                List<ItemStack> matStacks = QTRecipeWrapper.getStacksForCustomMaterial(material);

                //We don't allow further gear customizability since for performance the JEI plugin only looks through all registered items, not through all combinations of item, metadata and NBT
                //We also don't allow oreDict entries for the gear, as that similarly doesnt make a lot of sense
                Item gearItem = null;
                if (json.get("tool").isJsonObject())
                    gearItem = gson.fromJson(JsonUtils.getJsonObject(json, "tool"), CustomMaterial.class).item;
                else {
                    String itemName = JsonUtils.getString(json, "tool");
                    if (itemName.equalsIgnoreCase("any"))
                        universalMaterial = material;
                    else
                        gearItem = Item.REGISTRY.getObject(new ResourceLocation(itemName));
                }

                if (gearItem != null) customMaterials.put(gearItem, matStacks);
            }
        } catch (Exception ignored) {}
    }
}
