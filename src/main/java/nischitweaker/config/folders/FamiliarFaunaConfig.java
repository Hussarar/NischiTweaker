package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

//@MixinConfig(name = Tags.MODID)
public class FamiliarFaunaConfig {
    @Config.Comment({
            "Maximum height for pixie spawning",
            "Set to -1 to disable mixin (requires restart)"
    })
    @Config.Name("Pixie Max Spawn Height (Mixin Toggle)")
    @Config.RangeInt(min = -1, max = 256)
    public int pixieMaxSpawnHeight = 110;
}
