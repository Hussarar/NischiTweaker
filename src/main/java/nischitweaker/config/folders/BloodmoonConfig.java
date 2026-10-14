package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

//@MixinConfig(name = Tags.MODID)
public class BloodmoonConfig {

    @Config.Comment({
            "Prevent bloodmoons if all players are below this SRP evolution phase (and evolution is enabled)",
            "Disable mixin by setting to -1 (requires restart)"
    })
    @Config.Name("Bloodmoon Minimum Phase (Mixin Toggle)")
    @Config.RangeInt(min = -1, max = 10)
    @Config.SlidingOption
    public int bloodmoonMinPhase = 3;
}
