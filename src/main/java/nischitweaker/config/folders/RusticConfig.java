package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class RusticConfig {

    @Config.Comment({
            "Makes hopper support for Brewing Barrels better: ",
            "Top hopper: juice in, Back: empty bottles for new wine, Side: old wine, Bottom: all output slots."
    })
    @Config.Name("Hopper go Brrew (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.rustic.hoppergobrrew.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "rustic", desired = true, warnIngame = false, reason = "Compat for Rustic")
    public boolean hopperGoBrrew = true;
}
