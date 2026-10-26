package nischitweaker;

import fermiumbooter.util.FermiumJarScanner;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import nischitweaker.asm.zenutils.ConfigAnytimeClassTransformer;
import nischitweaker.asm.indev.bloodmoon.BloodmoonDevMappingsTransformer;
import nischitweaker.asm.DistantHorizonsMixinConnectorTransformer;
import nischitweaker.asm.EnchanterToolsClassTransformer;
import nischitweaker.asm.indev.invtweaks.InvTweaksDevAccessTransformer;
import nischitweaker.asm.zenutils.ZenUtilsShareTransformer;
import nischitweaker.config.ConfigHandler;
import nischitweaker.config.folders.VanillaConfig;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class NischiTweakerPlugin implements IFMLLoadingPlugin {

	public NischiTweakerPlugin() {
		//FermiumBooter only does this in its injectData, scanning before that misses indev coremod dependencies (locks, enchanter_tools, rlmixins, ...)
		//Reflection since it only exists since FermiumBooter 1.6.0, not needed anymore since 1.6.4 (but harmless)
		//No version check here, checking the version through the jar scanner would trigger the broken scan before the fix
		if(CompatUtil.isDev()) {
			try {
				Class.forName("fermiumbooter.util.ClasspathModHandler").getMethod("fixIndevForceLoadAsModDependencies").invoke(null);
			} catch(ReflectiveOperationException ignored) {}
		}

		if(CompatUtil.isInRange("zenutils", "(1.26.0,1.27.5]")) {
			Launch.classLoader.registerTransformer(ConfigAnytimeClassTransformer.class.getName());
			Launch.classLoader.registerTransformer(ZenUtilsShareTransformer.class.getName());
		}
		if(FermiumJarScanner.isModPresent("distanthorizons"))
			Launch.classLoader.registerTransformer(DistantHorizonsMixinConnectorTransformer.class.getName());

		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.dependencies.json", false, ConfigHandler.modContainers.removedDependencies.values());
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.enchantertools.inkwell.early.json", false, ConfigHandler.enchanterTools.inkwellReplaceChance, "enchanter_tools");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.vanilla.frostwalkermulti.json", false, () -> ConfigHandler.vanilla.frostWalkerMulti != 1F);
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.vanilla.newworlddifficulty.json", false, () -> ConfigHandler.vanilla.newWorldDifficulty != VanillaConfig.Difficulty.NORMAL);

		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.enchantertools.comprehension.json", true, ConfigHandler.enchanterTools.comprehensionChance, "enchanter_tools");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.bloodmoon.disablebeforephase.json", true, ConfigHandler.bloodmoon.bloodmoonMinPhase, "bloodmoon", "srparasites", "srpmixins");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.champions.dmgscaling.json", true, ConfigHandler.champions.flatDamageAmount, "champions");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.champions.parasitechance.json", true, ConfigHandler.champions.championParasiteChanceIncrease, "champions", "srparasites", "srpmixins");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.ebwizardry.unbreakableblocks.json", true, ConfigHandler.ebwizardry.wizardryUnbreakableBlocks, "ebwizardry");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.familiarfauna.pixiespawnheight.json", true, ConfigHandler.familiarfauna.pixieMaxSpawnHeight, "familiarfauna");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.infernalmobs.parasitechance.json", true, ConfigHandler.infernalmobs.infernalParasiteChanceIncrease, "infernalmobs", "srparasites", "srpmixins");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.locks.lockpickingpitch.json", true, () -> Math.abs(ConfigHandler.locks.lockPickingPitchMultiplier - 1.25F) > 1e-3, "locks");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.netherapi.biomesize.json", true, () -> ConfigHandler.netherapi.netherBiomeSize != 3, "nether_api");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.reskillable.goldenosmosis.json", true, ConfigHandler.reskillable.goldenOsmosisRepairableItems, "reskillable");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.variedcommodities.musketloadtime.json", true, () -> ConfigHandler.variedcommodities.musketLoadingTime != 60, "variedcommodities");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.xptome.greatertome.json", true, ConfigHandler.xptome.additionalTomes.keySet(), "xpbook");
        CompatUtil.conditionallyAddMixin("mixin.nischitweaker.locks.lockpickstrengths.json", true, ConfigHandler.locks.lockPickStrengths.keySet(), "locks");
        CompatUtil.conditionallyAddMixin("mixin.nischitweaker.locks.lockstats.json", true, ConfigHandler.locks.lockStats.keySet(), "locks");
        CompatUtil.conditionallyAddMixin("mixin.nischitweaker.globalgamerules.appliedrules.json", true, ConfigHandler.globalgamerules.appliedGameRules, "globalgamerules");

		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.locks.devgetitems.early.json", false, CompatUtil::isDev, "locks");
		CompatUtil.conditionallyAddMixin("mixin.nischitweaker.grapplinghook.devcaptureposition.json", true, CompatUtil::isDev, "grapplemod");
		if(CompatUtil.isDev() && FermiumJarScanner.isModPresent("bloodmoon"))
			Launch.classLoader.registerTransformer(BloodmoonDevMappingsTransformer.class.getName());
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{
				EnchanterToolsClassTransformer.class.getName()
		};
	}
	
	@Override public String getModContainerClass() {return null;}
	@Override public String getSetupClass() {return null;}
	@Override public void injectData(Map<String, Object> data) {}
	
	@Override
	public String getAccessTransformerClass()
	{
		if(CompatUtil.isDev() && FermiumJarScanner.isModPresent("inventorytweaks"))
			return InvTweaksDevAccessTransformer.class.getName();
		return null;
	}
}