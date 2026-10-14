package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class XPTomeConfig {

    @Config.Comment("Adds a Greater XP Tome that can store up to 100 levels (30970 XP) instead of the normal tome's 30 levels (1395 XP).")
    @Config.Name("Greater XP Tome (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.xptome.greatertome.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "xpbook", desired = true, warnIngame = false, reason = "Greater XP Tome requires XP Tome")
    public boolean greaterTome = true;

    @Config.Comment("How much XP the Greater XP Tome should hold. Default 30970 = 100 lvls.")
    @Config.Name("Greater XP Tome Size")
    @Config.RangeInt(min = 0)
    public int maxStorage = 30970;
}
