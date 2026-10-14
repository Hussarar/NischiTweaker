package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

//@MixinConfig(name = Tags.MODID)
public class InfernalMobsConfig {

    @Config.Comment({
            "Infernal spawn chance increase for parasites in phase 9+ (0.5 = 50% increase)",
            "Set to 0 to disable mixin (requires restart)"
    })
    @Config.Name("Infernal Parasite Chance Increase (MixinToggle)")
    @Config.RangeDouble(min = 0.0)
    public float infernalParasiteChanceIncrease = 0.5F;
}
