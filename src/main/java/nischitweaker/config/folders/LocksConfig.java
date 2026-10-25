package nischitweaker.config.folders;

import com.google.common.collect.ImmutableMap;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@MixinConfig(name = Tags.MODID)
public class LocksConfig {

    @Config.Comment({
            "Lock picking close-to-fail pitch multiplier",
            "Disable mixin by setting to the default value of 1.25 (requires restart)."
    })
    @Config.Name("Lock Picking Pitch Multiplier")
    @Config.RangeDouble(min = 0.0, max = 2.0)
    public float lockPickingPitchMultiplier = 0.5F;

    @Config.Comment({
            "Add more lockpicks with strengths",
            "Strength being the chance to not break, as long as no enchantments are involved",
            "Disable mixin by clearing the list",
            "By default adds a dragonbone lockpick with 97% survival chance.",
            "For more picks you'll have to add a lang key, an item model json, a gui texture, an item texture and a recipe json."
    })
    @Config.Name("Additional Lock Picks(MixinToggle)")
    @Config.RequiresMcRestart
    public Map<String, Float> additionalLockpicks = new LinkedHashMap<>(ImmutableMap.of(
            "dragonbone_lock_pick", 0.97F
    ));
    @Config.Comment({
            "Add more locks with custom stats.",
            "Format: name:length:enchantValue:resistance",
            "Example: dragonbone_lock", "15:25:80.0" ,
            "Disable mixin by clearing the list."
    })
    @Config.Name("Additional Locks(MixinToggle)")
    @Config.RequiresMcRestart
    public Map<String, String> additionalLocks = new LinkedHashMap<>(ImmutableMap.of(
            "dragonbone_lock", "12:25:80.0" //15 Pins, 25 Enchant Value, 80.0 Resistance
    ));

    @Config.Comment({
            "Change the strength of lock picks by name, e.g. diamond_lock_pick=0.1",
            "Strength being the chance to not break, as long as no enchantments are involved",
            "It also limits the lock complexity a pick can open: Complexity I needs more than 0.25, II more than 0.5, III more than 0.75",
            "Picks from Additional Lock Picks can be listed too. Picks not listed keep their strength.",
            "Disable mixin by clearing the list (requires restart)."
    })
    @Config.Name("Lock Pick Strengths(MixinToggle)")
    @Config.RequiresMcRestart
    public Map<String, Float> lockPickStrengths = new LinkedHashMap<>(ImmutableMap.of(
            "wood_lock_pick", 0.2F,
            "iron_lock_pick", 0.35F,
            "steel_lock_pick", 0.7F,
            "gold_lock_pick", 0.25F,
            "diamond_lock_pick", 0.85F
    ));
}
