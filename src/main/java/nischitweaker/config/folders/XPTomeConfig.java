package nischitweaker.config.folders;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.common.config.Config;

import java.util.LinkedHashMap;
import java.util.Map;

//@MixinConfig(name = Tags.MODID)
public class XPTomeConfig {

    @Config.Comment({
            "Adds more XP Tomes with the given names and xp storage abilities.",
            "To add more, you will need to provide a lang key, a texture, an item model json and a recipe json.",
            "Default: greater_xp_book with 30970 = 100 lvl storage",
            "Clear map to disable mixin."
    })
    @Config.Name("Additional XP Tomes (MixinToggle)")
    @Config.RequiresMcRestart
    public Map<String, Integer> additionalTomes = new LinkedHashMap<>(ImmutableMap.of(
            "greater_xp_book", 30970
    ));
}
