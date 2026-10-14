package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class SimpleDifficultyConfig {

    @Config.Comment("Apply thirst after iframes check so it only gets decreased on successful attacks")
    @Config.Name("Thirst IFrames Fix (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.simpledifficulty.thirstiframes.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "simpledifficulty", desired = true, warnIngame = false, reason = "Compat for Simple Difficulty")
    public boolean thirstIFramesFix = true;
}
