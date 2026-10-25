package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

import java.util.LinkedHashSet;
import java.util.Set;

@MixinConfig(name = Tags.MODID)
public class GlobalGameRulesConfig {

    @Config.Comment({
            "Only apply and save Global GameRules on the server overworld instead of every dimension and client world",
            "Fixes gamerule changes reverting when another dimension loads and client worlds writing stale gamerules into the config"
    })
    @Config.Name("Only Server Overworld (MixinToggle)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.globalgamerules.serveroverworldonly.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "globalgamerules", desired = true, warnIngame = false, reason = "Compat for Global GameRules")
    public boolean onlyServerOverworld = true;

    @Config.Comment({
            "Only these gamerules will be applied from the Global GameRules config on world load, all others stay per world",
            "Custom gamerules of Global GameRules are always applied",
            "Empty list to disable mixin (requires restart)"
    })
    @Config.Name("Applied Gamerules (MixinToggle)")
    public Set<String> appliedGameRules = new LinkedHashSet<>();
}
