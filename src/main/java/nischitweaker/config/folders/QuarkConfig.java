package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class QuarkConfig {

    @Config.Comment("Fix quark shulker dropoff not using the correct slot making it fail in certain situations")
    @Config.Name("Shulker Dropoff Fix (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.autoreglib.shulkerdropoff.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "autoreglib", desired = true, warnIngame = false, reason = "Compat for Quark/AutoRegLib")
    public boolean shulkerDropoffFix = true;
}
