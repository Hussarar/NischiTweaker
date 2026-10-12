package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import meldexun.betterconfig.api.Order;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class EnchanterToolsConfig {
    @Config.Comment("Adds a chance to replace a librarians enchanted book trade with a max enchanted inkwell instead.")
    @Config.Name("Enchanted Inkwell Villager Trades (MixinToggle)")
    @MixinConfig.MixinToggle(earlyMixin = "mixins.nischitweaker.enchantertools.inkwell.early.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", desired = true, warnIngame = false, reason = "Enchanted Inkwell mixin requires Enchanter Tools")
    @Order(0)
    public boolean enableEnchantedInkwellMixin = true;

    @Config.Comment("Chance to replace enchanted book trades with enchanted inkwells.")
    @Config.Name("Enchanted Inkwell Chance")
    @Config.RangeDouble(min = 0, max = 1)
    @Config.SlidingOption
    @Order(1)
    public double inkwellReplaceChance = 0.01;

    @Config.Comment("Makes Comprehension scale xp gains by +50% per lvl instead of scaling as x1.5, x3, x4.5 etc.")
    @Config.Name("Modify Comprehension Scaling (MixinToggle")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.nischitweaker.enchantertools.comprehension.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "enchanter_tools", targetVersionRange = "(,1.1.1a]", desired = true, warnIngame = false, reason = "Compat for Enchanter Tools")
    @Order(2)
    public boolean modifyComprehensionScaling = true;
}
