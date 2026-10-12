package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class EnchanterToolsConfig {
    @Config.Comment("Makes Comprehension scale xp gains by +50% per lvl instead of scaling as x1.5, x3, x4.5 etc.")
    @Config.Name("Modify Comprehension Scaling")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.nischitweaker.enchantertools.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", targetVersionRange = "(,1.1.1a]", desired = true, warnIngame = false, reason = "Compat for Enchanter Tools")
    public boolean modifyComprehensionScaling = true;
}
