package nischitweaker.features;

import bl4ckscor3.mod.xptome.ItemXPTome;
import bl4ckscor3.mod.xptome.XPTome;
import net.minecraft.util.ResourceLocation;
import nischitweaker.config.ConfigHandler;

public class GreaterXPTome {
    public static final String STORED_KEY = "StoredXP";

    public static final ItemXPTome GREATER_XP_TOME = (ItemXPTome) new ItemXPTome()
            .setRegistryName(new ResourceLocation(XPTome.MODID, "greater_xp_book"))
            .setTranslationKey(XPTome.MODID + ":greater_xp_book")
            .setMaxDamage(ConfigHandler.xptome.maxStorage);
}
