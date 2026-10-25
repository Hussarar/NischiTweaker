package nischitweaker.config.folders;

import com.google.common.collect.ImmutableMap;
import fermiumbooter.annotations.MixinConfig;
import meldexun.betterconfig.api.Order;
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
            "Each entry is the name of the lock (registry name without 'locks:') with its pins, enchantability and blast resistance.",
            "Add the name to the Generated Locks lists in locks.cfg if the lock should generate on chests.",
            "Disable mixin by clearing the list."
    })
    @Config.Name("Additional Locks(MixinToggle)")
    @Config.RequiresMcRestart
    public Map<String, LockData> additionalLocks = new LinkedHashMap<>(ImmutableMap.of(
            "dragonbone_lock", new LockData(12, 25, 80.0F)
    ));

    public static class LockData {
        @Config.Name("Pins")
        @Config.Comment("Number of pins of the lock.")
        @Config.RangeInt(min = 1, max = 15)
        @Order(0)
        public int pins = 8;

        @Config.Name("Enchantability")
        @Config.Comment({
                "Enchantability of the lock, higher means better enchantments when enchanting it",
        })
        @Order(1)
        public int enchantability = 10;

        @Config.Name("Blast Resistance")
        @Config.Comment({
                "Blast resistance of a block while it is locked with this lock. The higher of this and the block's own resistance is used.",
        })
        @Order(2)
        public float resistance = 10.0F;

        public LockData() {}

        public LockData(int pins, int enchantability, float resistance) {
            this.pins = pins;
            this.enchantability = enchantability;
            this.resistance = resistance;
        }
    }

    @Config.Comment({
            "Change the strength of lock picks by name, e.g. diamond_lock_pick=0.1",
            "Strength being the chance to not break, as long as no enchantments are involved",
            "It also limits the lock complexity a pick can open: Complexity I needs more than 0.25, II more than 0.5, III more than 0.75",
            "Picks from Additional Lock Picks can be listed too. Picks not listed keep their strength.",
            "Disable mixin by clearing the list."
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