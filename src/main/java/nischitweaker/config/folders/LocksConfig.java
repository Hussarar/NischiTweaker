package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class LocksConfig {

    @Config.Comment({
            "Lock picking close-to-fail pitch multiplier",
            "Disable mixin by setting to the default value of 1.25 (requires restart)."
    })
    @Config.Name("Lock Picking Pitch Multiplier")
    @Config.RangeDouble(min = 0.0, max = 2.0)
    public float lockPickingPitchMultiplier = 0.5F;

    @Config.Comment("Add dragonbone lock pick item")
    @Config.Name("Dragonbone Lock Pick (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.locks.dragonbonelockpick.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "locks", desired = true, warnIngame = false, reason = "Compat for Locks")
    public boolean dragonboneLockPick = true;

    @Config.Comment("This is the chance to not break, as long as no enchantments are involved")
    @Config.Name("Dragonbone Lock Pick Strength")
    @Config.RangeDouble(min = 0.0, max = 1.0)
    public float dragonboneLockPickDurability = 0.97F;
}
