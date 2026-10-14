package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class GrapplingHookConfig {

    @Config.Comment("Add visible item cooldown to Ender Staff")
    @Config.Name("Ender Staff Cooldown (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.grapplinghook.cooldown.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "grapplemod", desired = true, warnIngame = false, reason = "Compat for Grappling Hook Mod")
    public boolean enderStaffCooldown = true;
}
