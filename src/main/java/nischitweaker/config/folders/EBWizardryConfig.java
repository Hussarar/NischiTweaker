package nischitweaker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;
import nischitweaker.Tags;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@MixinConfig(name = Tags.MODID)
public class EBWizardryConfig {

    //TODO: add complete feature toggles for armor, armor upgrades, scrolls etc so this is not so dispersed
    @Config.Comment("Remove various wizard trades (arcane tomes, armor, wand upgrades)")
    @Config.Name("Modify Wizard Trades (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.ebwizardry.wizardtrades.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "ebwizardry", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry")
    public boolean modifyWizardTrades = true;

    @Config.Comment("Set arcane tome max stack size to 16")
    @Config.Name("Arcane Tome Stack Size (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.ebwizardry.arcanetomestacksize.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "ebwizardry", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry")
    public boolean arcaneTomeStackSize = true;

    @Config.Comment("Remove scroll and armor upgrade recipes from JEI")
    @Config.Name("Disable Scroll & Armor Upgrade JEI Recipes (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.ebwizardry.jeiworkbench.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "ebwizardry", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry")
    @MixinConfig.CompatHandling(modid = "jei", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry x JEI")
    public boolean jeiWorkbenchRecipes = true;

    @Config.Comment("Remove armor recipes from JEI imbuement altar category")
    @Config.Name("JEI Imbuement Recipes (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.ebwizardry.jeiimbuement.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "ebwizardry", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry")
    @MixinConfig.CompatHandling(modid = "jei", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry x JEI")
    public boolean jeiImbuementRecipes = true;

    @Config.Comment("Remove ebwizardry enchantment info pages from JEI")
    @Config.Name("Remove Enchantments from JEI (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.ebwizardry.jeienchantments.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "ebwizardry", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry")
    @MixinConfig.CompatHandling(modid = "jei", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry x JEI")
    public boolean jeiEnchantmentInfo = true;

    @Config.Comment("Remove mana flask charging recipes for wizard armor")
    @Config.Name("Disable Armor Charging (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.ebwizardry.disablearmorcharging.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "ebwizardry", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry")
    public boolean armourJeiRegistration = true;

    @Config.Comment("Disable armor and wand drops from wizards and evil wizards")
    @Config.Name("Disable Wizard Gear Drops (MixinToggle)")
    @MixinConfig.MixinToggle(lateMixin = "mixin.nischitweaker.ebwizardry.wizarddrops.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "ebwizardry", desired = true, warnIngame = false, reason = "Compat for Electroblob's Wizardry")
    public boolean wizardDrops = true;

    @Config.Comment({
            "Blocks that wizardry spells cannot break",
            "Leave empty to disable mixin (requires restart)"
    })
    @Config.Name("Wizardry Unbreakable Blocks (Mixin Toggle)")
    public Set<String> wizardryUnbreakableBlocks = new LinkedHashSet<>(Arrays.asList(
            "minecraft:mob_spawner",
            "minecraft:chest"
    ));
}
