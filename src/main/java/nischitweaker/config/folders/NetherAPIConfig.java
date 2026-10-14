package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class NetherAPIConfig {

    @Config.Comment("Make (default) BetterNether Bone Reef more common, otherwise it basically doesn't spawn at all when using Nether API.")
    @Config.Name("Fix Bone Reef Rarity (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.netherapi.bonereef.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "nether_api", desired = true, warnIngame = false, reason = "Compat for Nether API")
    @MixinConfig.CompatHandling(modid = "betternether", modName = "Better Nether", desired = true, warnIngame = false, reason = "Compat for Better Nether")
    public boolean fixBoneReefRarity = true;

    @Config.Comment({
            "How big nether biomes will be.",
            "Set to 3 (default) to disable mixin (requires restart)."
    })
    @Config.Name("Nether Biome Size")
    @Config.RangeInt(min = 1)
    public int netherBiomeSize = 1;
}
