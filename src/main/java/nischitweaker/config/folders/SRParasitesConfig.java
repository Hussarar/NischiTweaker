package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class SRParasitesConfig {

    @Config.Comment("Ignore light level spawn check for parasites in End dimension")
    @Config.Name("Light Ignore in End Dim (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.srparasites.endlightcheck.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "srparasites", desired = true, warnIngame = false, reason = "Compat for SRParasites")
    public boolean endLightCheckSkip = true;

    @Config.Comment("Add Pivot effect to nearby parasites when Dispatcher Nidus fails to create a Dispatcher entity")
    @Config.Name("Nidus Spreads Pivot (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.srparasites.niduspivot.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "srparasites", desired = true, warnIngame = false, reason = "Compat for SRParasites")
    public boolean dispatcherNidusPivot = true;
}
