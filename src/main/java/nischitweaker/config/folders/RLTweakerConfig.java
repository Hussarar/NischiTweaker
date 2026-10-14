package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class RLTweakerConfig {

    @Config.Comment("Makes Curse Break tomes actually work when right clicking")
    @Config.Name("Fix Curse Break Tomes (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.rltweaker.cursebreak.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "rltweaker", desired = true, warnIngame = false, reason = "Compat for RLTweaker")
    @MixinConfig.CompatHandling(modid = "charm", desired = true, warnIngame = false, reason = "Compat for RLTweaker requires Charm")
    public boolean curseBreakTome = true;

    @Config.Comment("Fix Drowneds spamming log about added door break AI")
    @Config.Name("Drowned LogSpam Fix (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.rltweaker.drownedfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "rltweaker", desired = true, warnIngame = false, reason = "Compat for RLTweaker")
    @MixinConfig.CompatHandling(modid = "oe", desired = true, warnIngame = false, reason = "Compat for RLTweaker requires Oceanic Expanse")
    public boolean drownedEntityFix = true;
}
