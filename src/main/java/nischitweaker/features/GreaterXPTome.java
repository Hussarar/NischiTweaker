package nischitweaker.features;

import bl4ckscor3.mod.xptome.ItemXPTome;
import bl4ckscor3.mod.xptome.XPTome;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import nischitweaker.Tags;
import nischitweaker.config.ConfigHandler;
import nischitweaker.recipes.RecipeGreaterXPTomeUpgrade;

import java.util.HashMap;
import java.util.Map;

public class GreaterXPTome {
    public static final String STORED_KEY = "StoredXP";
    public static final Map<String, ItemXPTome> XPTomes = new HashMap<>();

    public static void registerAdditionalTomes(RegistryEvent.Register<Item> event) {
        ConfigHandler.xptome.additionalTomes.forEach((name, maxStorage) -> {
            ItemXPTome tome = (ItemXPTome) new ItemXPTome()
                    .setRegistryName(new ResourceLocation(XPTome.MODID, name))
                    .setTranslationKey(XPTome.MODID + ":"+ name)
                    .setMaxDamage(maxStorage);
            XPTomes.put(name, tome);
            event.getRegistry().register(tome);
        });
    }

    public static int getStoredXP(ItemStack stack) {
        if (!(stack.getItem() instanceof ItemXPTome)) return 0;
        if (stack.getItem() == XPTome.XP_BOOK) return Math.max(0, stack.getMaxDamage() - stack.getItemDamage());

        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null && tag.hasKey(STORED_KEY)) return tag.getInteger(STORED_KEY);
        return 0;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if(stack.getItem() instanceof ItemXPTome) {
            @SuppressWarnings("deprecation")
            String toRemove = I18n.translateToLocalFormatted("item.durability", stack.getMaxDamage() - stack.getItemDamage(), stack.getMaxDamage());
            event.getToolTip().removeIf(toRemove::equals);
        }
    }

    @SubscribeEvent
    public static void onRecipe(RegistryEvent.Register<IRecipe> event) {
        ConfigHandler.xptome.additionalTomes.keySet().forEach(name -> {
            IRecipe original = event.getRegistry().getValue(new ResourceLocation(Tags.MODID, name));
            if(!(original instanceof ShapedOreRecipe)) {
                Tags.LOGGER.warn("Additional XP Tome Recipe json not found for xpbook:{}", name);
                return;
            }
            CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
            primer.width = ((ShapedOreRecipe) original).getRecipeWidth();
            primer.height = ((ShapedOreRecipe) original).getRecipeHeight();
            primer.input = original.getIngredients();
            event.getRegistry().register(new RecipeGreaterXPTomeUpgrade(original, primer)); // this overrides the json defined recipe, only works bc thats allowed to recipes
        });
    }
}
