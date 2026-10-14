package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class InControlConfig {
    @Config.Comment({
            "Fixes rules using \"mod\".",
            "If your installed profile is in a path containing \"curseforge\", all mobs would be matched to forge -> minecraft modid by incontrols modid check."
    })
    @Config.Name("Fix mod rules (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.incontrol.modrules.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "incontrol", modName = "InControl", desired = true, warnIngame = false, reason = "Fix for InControl")
    public boolean fixGettingModid = true;
}
