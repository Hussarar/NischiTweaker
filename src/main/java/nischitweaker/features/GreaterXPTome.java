package nischitweaker.features;

import bl4ckscor3.mod.xptome.ItemXPTome;
import bl4ckscor3.mod.xptome.XPTome;
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

public class GreaterXPTome {
    public static final String STORED_KEY = "StoredXP";
    public static final String NAME = "greater_xp_book";
    public static ShapedOreRecipe recipe;

    public static final ItemXPTome GREATER_XP_TOME = (ItemXPTome) new ItemXPTome()
            .setRegistryName(new ResourceLocation(XPTome.MODID, NAME))
            .setTranslationKey(XPTome.MODID + ":"+ NAME)
            .setMaxDamage(ConfigHandler.xptome.maxStorage);

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
        IRecipe original = event.getRegistry().getValue(new ResourceLocation(Tags.MODID, NAME));
        if(!(original instanceof ShapedOreRecipe)) {
            Tags.LOGGER.warn("Greater XP Tome Recipe json not found");
            return;
        }
        CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
        primer.width = ((ShapedOreRecipe) original).getRecipeWidth();
        primer.height = ((ShapedOreRecipe) original).getRecipeHeight();
        primer.input = original.getIngredients();
        recipe = new RecipeGreaterXPTomeUpgrade(original, primer);
        event.getRegistry().register(recipe); // this overrides the json defined recipe, only works bc thats allowed to recipes
    }
}
