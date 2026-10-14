package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class SereneSeasonsConfig {

    @Config.Comment("Fix serene seasons not checking simpledifficulty dimension whitelist to change temperature")
    @Config.Name("Temperature Dimension Fix (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.sereneseasons.temperaturefix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "sereneseasons", desired = true, warnIngame = false, reason = "Compat for Serene Seasons x Simple Difficulty")
    @MixinConfig.CompatHandling(modid = "simpledifficulty", desired = true, warnIngame = false, reason = "Compat for Serene Seasons x Simple Difficulty")
    public boolean temperatureDimensionFix = true;
}
