package nischitweaker.config;

import meldexun.betterconfig.api.BetterConfig;
import meldexun.betterconfig.api.BetterConfigManager;
import meldexun.betterconfig.api.LoadEarly;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nischitweaker.Tags;
import nischitweaker.config.folders.*;

@BetterConfig(
		modid = Tags.MODID,
		version = Tags.CFG_VERSION,
		bigCategoryComments = false,
		lowerCaseCategories = false,
		removeDeprecatedEntries = true
)
@LoadEarly
public class ConfigHandler {

	@Config.Name("In Control!")
	public static InControlConfig incontrol = new InControlConfig();

	@Config.Name("Distant Horizons")
	public static DistantHorizonsConfig distantHorizons = new DistantHorizonsConfig();

	@Config.Name("Champions")
	public static ChampionsConfig champions = new ChampionsConfig();

	@Config.Name("Mod Containers")
	public static ModContainerConfig modContainers = new ModContainerConfig();

	@Config.Name("ZenUtils")
	public static ZenUtilsConfig zenUtils = new ZenUtilsConfig();

	@Config.Name("Enchanter Tools")
	public static EnchanterToolsConfig enchanterTools = new EnchanterToolsConfig();

	@Mod.EventBusSubscriber
	private static class EventHandler{
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(Tags.MODID))
				BetterConfigManager.sync(Tags.MODID);
		}
	}
}