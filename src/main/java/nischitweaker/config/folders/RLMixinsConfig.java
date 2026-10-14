package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class RLMixinsConfig {

    @Config.Comment("Reduce nausea movement penalty")
    @Config.Name("Nausea Reduction (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.rlmixins.nauseareduction.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "rlmixins", desired = true, warnIngame = false, reason = "Compat for RLMixins")
    public boolean nauseaReduction = true;
}
