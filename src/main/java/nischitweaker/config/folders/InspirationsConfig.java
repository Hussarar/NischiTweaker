package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class InspirationsConfig {

    @Config.Comment("Fix cauldron creating splashing tipped arrows on some potions (water, mundane etc)")
    @Config.Name("Cauldron Arrow Fix (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.inspirations.cauldronarrowfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "inspirations", desired = true, warnIngame = false, reason = "Compat for Inspirations")
    public boolean cauldronArrowFix = true;

    @Config.Comment("Prevent dye recipe when cauldron is boiling (to protect charm coffee brewing)")
    @Config.Name("Cauldron Coffee Protection (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.inspirations.cauldroncoffe.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "inspirations", desired = true, warnIngame = false, reason = "Compat for Inspirations")
    public boolean cauldronCoffeeProtection = true;
}
