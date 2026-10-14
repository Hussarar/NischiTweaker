package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

//@MixinConfig(name = Tags.MODID)
public class ChampionsConfig {
    @Config.Comment({
            "Champions will not be able to get more added damage than this value times the growthFactor of their rank.",
            "Example: A 5 star with growthFactor of 15 wont get more than +750 dmg.",
            "The breakpoint is reached at this value divided by champions attackDamage cfg,",
            " so by default 50/0.5 = 100 base dmg",
            "Disable mixin by setting to 0 (requires restart)"
    })
    @Config.Name("Damage Cap per growthFactor (Mixin Toggle)")
    @Config.RangeDouble(min = 0.0)
    public float flatDamageAmount = 50;

    @Config.Comment({
            "Champion spawn chance increase for parasites in phase 9+ (0.5 = 50% increase)",
            "Set to 0 to disable mixin (requires restart)"
    })
    @Config.Name("Champion Parasite Chance Increase (Mixin Toggle)")
    @Config.RangeDouble(min = 0.0)
    public float championParasiteChanceIncrease = 0.5F;
}
