package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

public class QualityToolsConfig {

    @Config.Comment({
            "Adds a JEI category for the Reforging Station, showing which materials can reforge which gear.",
            "Adds some time to startup."
    })
    @Config.Name("JEI Reforging Category")
    @Config.RequiresMcRestart
    public boolean jeiReforgingCategory = true;
}
