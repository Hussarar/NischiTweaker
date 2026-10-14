package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

//@MixinConfig(name = Tags.MODID)
public class VariedCommoditiesConfig {

    @Config.Comment({
            "Musket loading time in ticks (20 ticks = 1 second)",
            "Set to default 60 ticks to disable mixin (requires restart)"
    })
    @Config.Name("Musket Loading Time")
    @Config.RangeInt(min = 1, max = 200)
    public int musketLoadingTime = 20;
}
