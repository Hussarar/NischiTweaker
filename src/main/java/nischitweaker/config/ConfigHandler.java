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

	@Config.Name("Cherry On 1.12.2")
	public static CherryOnConfig cherryon = new CherryOnConfig();

	@Config.Name("Bloodmoon")
	public static BloodmoonConfig bloodmoon = new BloodmoonConfig();

	@Config.Name("Scape and Run: Parasites")
	public static SRParasitesConfig srparasites = new SRParasitesConfig();

	@Config.Name("Electroblob's Wizardry")
	public static EBWizardryConfig ebwizardry = new EBWizardryConfig();

	@Config.Name("Locks")
	public static LocksConfig locks = new LocksConfig();

	@Config.Name("Infernal Mobs")
	public static InfernalMobsConfig infernalmobs = new InfernalMobsConfig();

	@Config.Name("Varied Commodities")
	public static VariedCommoditiesConfig variedcommodities = new VariedCommoditiesConfig();

	@Config.Name("Familiar Fauna")
	public static FamiliarFaunaConfig familiarfauna = new FamiliarFaunaConfig();

	@Config.Name("Inspirations")
	public static InspirationsConfig inspirations = new InspirationsConfig();

	@Config.Name("Nether API")
	public static NetherAPIConfig netherapi = new NetherAPIConfig();

	@Config.Name("RLTweaker")
	public static RLTweakerConfig rltweaker = new RLTweakerConfig();

	@Config.Name("Simple Difficulty")
	public static SimpleDifficultyConfig simpledifficulty = new SimpleDifficultyConfig();

	@Config.Name("Wolf Armor")
	public static WolfArmorConfig wolfarmor = new WolfArmorConfig();

	@Config.Name("Grappling Hook")
	public static GrapplingHookConfig grapplinghook = new GrapplingHookConfig();

	@Config.Name("Serene Seasons")
	public static SereneSeasonsConfig sereneseasons = new SereneSeasonsConfig();

	@Config.Name("Bountiful Baubles")
	public static BountifulBaublesConfig bountifulbaubles = new BountifulBaublesConfig();

	@Config.Name("No Tree Punching")
	public static NoTreePunchingConfig notreepunching = new NoTreePunchingConfig();

	@Config.Name("Ice and Fire")
	public static IceAndFireConfig iceandfire = new IceAndFireConfig();

	@Config.Name("Reskillable")
	public static ReskillableConfig reskillable = new ReskillableConfig();

	@Config.Name("Rustic")
	public static RusticConfig rustic = new RusticConfig();

	@Config.Name("Quark")
	public static QuarkConfig quark = new QuarkConfig();

	@Config.Name("FermiumMixins")
	public static FermiumMixinsConfig fermiummixins = new FermiumMixinsConfig();

	@Config.Name("XP Tome")
	public static XPTomeConfig xptome = new XPTomeConfig();

	@Config.Name("Set Bonus")
	public static SetBonusConfig setbonus = new SetBonusConfig();

	@Config.Name("Inventory Tweaks")
	public static InvTweaksConfig invtweaks = new InvTweaksConfig();

	@Config.Name("RLMixins")
	public static RLMixinsConfig rlmixins = new RLMixinsConfig();

	@Config.Name("Level Up! Reloaded")
	public static LevelUp2Config levelup2 = new LevelUp2Config();

	@Config.Name("Global GameRules")
	public static GlobalGameRulesConfig globalgamerules = new GlobalGameRulesConfig();

	@Config.Name("Minecraft")
	public static VanillaConfig vanilla = new VanillaConfig();

    @Mod.EventBusSubscriber
	private static class EventHandler{
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(Tags.MODID))
				BetterConfigManager.sync(Tags.MODID);
		}
	}
}