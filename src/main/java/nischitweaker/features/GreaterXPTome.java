package nischitweaker.features;

import bl4ckscor3.mod.xptome.ItemXPTome;
import bl4ckscor3.mod.xptome.XPTome;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nischitweaker.config.ConfigHandler;

public class GreaterXPTome {
    public static final String STORED_KEY = "StoredXP";

    public static final ItemXPTome GREATER_XP_TOME = (ItemXPTome) new ItemXPTome()
            .setRegistryName(new ResourceLocation(XPTome.MODID, "greater_xp_book"))
            .setTranslationKey(XPTome.MODID + ":greater_xp_book")
            .setMaxDamage(ConfigHandler.xptome.maxStorage);

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
}
