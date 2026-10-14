package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class IceAndFireConfig {

    @Config.Comment("Post AnimalTameEvent when amphithere is tamed")
    @Config.Name("Amphithere Tame Event (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.iceandfire.amphitheretame.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "Compat for Ice and Fire")
    public boolean amphithereTameEvent = true;

    @Config.Comment("Post AnimalTameEvent when cockatrice is tamed")
    @Config.Name("Cockatrice Tame Event (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.iceandfire.cockatricetame.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "Compat for Ice and Fire")
    public boolean cockatriceTameEvent = true;
}
