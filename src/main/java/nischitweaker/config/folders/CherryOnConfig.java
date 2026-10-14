package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class CherryOnConfig {

    @Config.Comment("Removes all default archeology loot so it can be fully controlled by the cherryon treasure config")
    @Config.Name("Disable Default Archeology Loot (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.suikecherry.treasuredata.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "suikecherry", desired = true, warnIngame = false, reason = "Compat for Cherry On")
    public boolean disableDefaultTreasureLoot = true;

    @Config.Comment("Disables CherryOn overwriting various vanilla wood recipes for no reason.")
    @Config.Name("Disable Wood Recipe Modifications (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.suikecherry.craftrecipe.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "suikecherry", desired = true, warnIngame = false, reason = "Compat for Cherry On")
    public boolean disableWoodRecipeModifications = true;

    @Config.Comment("Fixes RTG compat crashing for default RTG, only for RTG plus and RTG Unofficial")
    @Config.Name("Fix RTG Biome Registration (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.suikecherry.rtgbiomes.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "suikecherry", desired = true, warnIngame = false, reason = "Compat for Cherry On")
    @MixinConfig.CompatHandling(modid = "rtg", modName = "Realistic Terrain Generation", desired = true, warnIngame = false, reason = "Compat for Cherry On x RTG")
    public boolean fixRTGBiomeRegistration = true;

    @Config.Comment("Fixes a clientside RTG compat method being called on remote servers leading to crashes.")
    @Config.Name("Fix Clientside call (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.suikecherry.rtgbiomename.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "suikecherry", desired = true, warnIngame = false, reason = "Compat for Cherry On")
    @MixinConfig.CompatHandling(modid = "rtg", desired = true, warnIngame = false, reason = "Compat for Cherry On x RTG")
    public boolean fixRTGBiomeName = true;
}
