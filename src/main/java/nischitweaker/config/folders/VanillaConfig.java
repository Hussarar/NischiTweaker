package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

public class VanillaConfig {
    @Config.Comment({
            "Increases frost walker frosted size by this amount. Big multipliers can be laggy.",
            "Disable mixin by setting to 1"
    })
    @Config.Name("Frost Walker Size Multiplier")
    @Config.RangeDouble(min = 0)
    public float frostWalkerMulti = 1.75F;
}
