package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

public class QualityToolsConfig {

    @Config.Comment({
            "Adds a JEI category for the Reforging Station, showing which materials can reforge which gear.",
            "Respects custom reforging materials and MaterialTweaker repair item overrides."
    })
    @Config.Name("JEI Reforging Category")
    @Config.RequiresMcRestart
    public boolean jeiReforgingCategory = true;

    @Config.Comment({
            "Shows the qualities the gear can get below each reforging recipe.",
            "Hovering a quality shows its reforging chance and attributes.",
            "With Luckified/RLLuck, qualities that become more likely with Luck get a clover icon.",
            "Requires JEI Reforging Category"
    })
    @Config.Name("JEI Reforging Category: Show Qualities")
    @Config.RequiresMcRestart
    public boolean jeiShowQualities = true;
}
