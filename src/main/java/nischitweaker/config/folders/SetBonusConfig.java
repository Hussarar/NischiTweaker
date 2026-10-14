package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class SetBonusConfig {

    @Config.Comment("Fix rare concurrency crash when rendering setbonus tooltips")
    @Config.Name("Concurrency Fix (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.setbonus.concurrencyfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "setbonus", desired = true, warnIngame = false, reason = "Compat for SetBonus")
    public boolean concurrencyFix = true;
}
