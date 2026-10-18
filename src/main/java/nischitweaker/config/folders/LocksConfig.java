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
}
