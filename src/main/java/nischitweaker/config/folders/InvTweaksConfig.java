package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class InvTweaksConfig {

    @Config.Comment("Fix autorefill sometimes throwing out items, especially on laggy servers")
    @Config.Name("Fix Autorefill (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.invtweaks.raceconditionfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "inventorytweaks", desired = true, warnIngame = false, reason = "Compat for Inventory Tweaks")
    public boolean raceConditionFix = true;
}
