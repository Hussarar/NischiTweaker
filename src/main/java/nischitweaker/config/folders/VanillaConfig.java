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

    @Config.Comment({
            "Difficulty newly created singleplayer worlds start with. Dedicated servers use server.properties instead",
            "Disable mixin by setting to NORMAL",
            "This is meant to replace Global GameRules' feature, as that updates the config after each world unload"
    })
    @Config.Name("New World Difficulty")
    public Difficulty newWorldDifficulty = Difficulty.NORMAL;

    //Own enum so the config doesn't load EnumDifficulty during coremod construction (obfuscated names aren't remapped yet)
    public enum Difficulty {
        PEACEFUL, EASY, NORMAL, HARD
    }
}
