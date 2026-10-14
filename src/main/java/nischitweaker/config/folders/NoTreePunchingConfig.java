package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class NoTreePunchingConfig {

    @Config.Comment("Saws are set to not be repairable, which doesnt stop mending but it does stop golden osmosis and anvil repairing. This removes that limitation.")
    @Config.Name("Make Saws Repairable (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.notreepunching.sawrepairable.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "notreepunching", desired = true, warnIngame = false, reason = "Compat for No Tree Punching")
    public boolean sawRepairable = true;
}
