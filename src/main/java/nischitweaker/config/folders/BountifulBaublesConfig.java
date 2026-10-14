package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

@MixinConfig(name = Tags.MODID)
public class BountifulBaublesConfig {

    @Config.Comment({
            "Fix crafting recipes using existing baubles changing the bauble modifier.",
            "In RLCraft this happens when changing backpack colors."
    })
    @Config.Name("Fix Crafting Changing Modifier (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.bountifulbaubles.craftingmodifierfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "bountifulbaubles", desired = true, warnIngame = false, reason = "Compat for Bountiful Baubles")
    public boolean craftModifierFix = true;
}
