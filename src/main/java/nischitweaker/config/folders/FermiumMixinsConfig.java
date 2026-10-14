package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import meldexun.betterconfig.api.Order;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class FermiumMixinsConfig {

    @Config.Comment("Configure Bountiful Baubles Broken Heart behavior")
    @Config.Name("Broken Heart Config (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.fermiummixins.brokenheart.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "fermiummixins", desired = true, warnIngame = false, reason = "Compat for FermiumMixins")
    @MixinConfig.CompatHandling(modid = "bountifulbaubles", desired = true, warnIngame = false, reason = "Compat for Bountiful Baubles")
    @Order(0)
    public boolean modifyBrokenHeart = true;

    @Config.Comment("Maximum damage that Broken Heart bauble can activate on (-1 for infinite)")
    @Config.Name("Broken Heart Max Damage")
    @Config.RangeDouble(min = -1.0)
    @Order(1)
    public double brokenHeartMaxDamage = 1000.0;

    @Config.Comment("Minimum health required on a body part for Broken Heart to activate")
    @Config.Name("Broken Heart Min Health")
    @Config.RangeInt(min = 0)
    @Order(2)
    public int brokenHeartMinHealth = 4;

    @Config.Comment("Configure Reskillable Undershirt behavior")
    @Config.Name("Undershirt Config (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.fermiummixins.undershirt.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "fermiummixins", desired = true, warnIngame = false, reason = "Compat for FermiumMixins")
    @MixinConfig.CompatHandling(modid = "reskillable", desired = true, warnIngame = false, reason = "Compat for Reskillable")
    @Order(3)
    public boolean modifyUndershirt = true;

    @Config.Comment("Maximum damage that Undershirt trait can activate on (-1 for infinite)")
    @Config.Name("Undershirt Max Damage")
    @Config.RangeDouble(min = -1.0)
    @Order(4)
    public double undershirtMaxDamage = 1000.0;

    @Config.Comment("Minimum health required on a body part for Undershirt to activate")
    @Config.Name("Undershirt Min Health")
    @Config.RangeDouble(min = 0.0)
    @Order(5)
    public double undershirtMinHealth = 4.0;
}
