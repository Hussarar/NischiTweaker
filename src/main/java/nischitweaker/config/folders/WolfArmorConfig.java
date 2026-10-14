package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class WolfArmorConfig {

    @Config.Comment("Remove wolf armor bonus roll in loot pools. Otherwise each luck point gives one extra wolf armor")
    @Config.Name("Disable Bonus Rolls (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.wolfarmor.disablebonusrolls.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "wolfarmor", desired = true, warnIngame = false, reason = "Compat for Wolf Armor and Storage")
    public boolean disableBonusRolls = true;
}
