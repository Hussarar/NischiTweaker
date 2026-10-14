package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import meldexun.betterconfig.api.Order;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class EnchanterToolsConfig {
    @Config.Comment({
            "Chance to replace enchanted book trades with enchanted inkwell trades (with max ench lvl) on librarians.",
            "Set to 0 to disable the mixin (requires restart)."
    })
    @Config.Name("Enchanted Inkwell Chance (MixinToggle)")
    @Config.RangeDouble(min = 0, max = 1)
    @Config.SlidingOption
    @Order(1)
    public float inkwellReplaceChance = 0.01F;

    @Config.Comment("Makes Comprehension scale xp gains by +x% per lvl instead of scaling as x1.5, x3, x4.5 etc. Also allows to set a chance to get it.")
    @Config.Name("Modify Comprehension (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.enchantertools.comprehension.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", targetVersionRange = "(,1.1.1a]", desired = true, warnIngame = false, reason = "Compat for Enchanter Tools")
    @Order(2)
    public boolean modifyComprehensionScaling = true;

    @Config.Comment({
            "By how much to increase experience gains when comprehension is active.",
            "Requires \"Modify Comprehension\"."
    })
    @Config.Name("Comprehension XP Scale")
    @Order(3)
    @Config.RangeDouble(min = 0)
    @Config.SlidingOption
    public float comprehensionScale = 0.35F;

    @Config.Comment("Chance to get Comprehension when using an XP Bottle. Requires \"Modify Comprehension\"")
    @Config.Name("Comprehension Apply Chance")
    @Order(4)
    @Config.RangeDouble(min = 0, max = 1)
    @Config.SlidingOption
    public float comprehensionChance = 0.15F;

    @Config.Comment("Adds a usage falloff system to the pondering orb (repeated use causes nausea/fear).")
    @Config.Name("Pondering Orb Falloff (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.enchantertools.ponderingorb.falloff.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", desired = true, warnIngame = false, reason = "Compat for Enchanter Tools")
    @Order(5)
    public boolean ponderingOrbFalloff = true;

    @Config.Comment("Makes the Pondering Orb drain XP points instead of levels.")
    @Config.Name("Pondering Orb Uses XP (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.enchantertools.ponderingorb.usexp.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", desired = true, warnIngame = false, reason = "Compat for Enchanter Tools")
    @MixinConfig.CompatHandling(modid = "reskillable", desired = true, warnIngame = false, reason = "Compat for Enchanter Tools uses Reskillable method")
    @Order(6)
    public boolean ponderingOrbUsesXP = true;

    @Config.Comment("Makes Inkwells added to loot pools be unenchanted.")
    @Config.Name("Loot Inkwells Unenchanted (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.enchantertools.inkwell.loot.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", desired = true, warnIngame = false, reason = "Compat for Enchanter Tools")
    @Order(7)
    public boolean disableInkwellLoot = true;

    @Config.Comment("Fixes a bug in EnchanterTools 1.1.1a making enchanting with no bookshelves impossible for some items.")
    @Config.Name("Fix Min Enchanting Lvl (Mixin & ASM Toggle")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(earlyMixin = "mixin.nischitweaker.enchantertools.minenchlvl.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", targetVersionRange = "1.1.1a", desired = true, warnIngame = false, reason = "Compat for Enchanter Tools")
    @Order(8)
    public boolean fixMinEnchantLvl = true;
}
