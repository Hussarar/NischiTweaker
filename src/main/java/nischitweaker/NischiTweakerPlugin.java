package nischitweaker;

import fermiumbooter.FermiumRegistryAPI;
import fermiumbooter.util.FermiumJarScanner;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import nischitweaker.asm.ConfigAnytimeClassTransformer;
import nischitweaker.asm.distanthorizons.DistantHorizonsMixinConnectorTransformer;
import nischitweaker.asm.enchantertools.EnchanterToolsClassTransformer;
import nischitweaker.asm.zenutils.ZenUtilsShareTransformer;
import nischitweaker.config.ConfigHandler;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class NischiTweakerPlugin implements IFMLLoadingPlugin {

	public NischiTweakerPlugin() {
		if(FermiumJarScanner.isModPresent("zenutils")) { //TODO: idk why fermiumbooter doesnt have a public access to mixintoggles but technically this should both only apply for zenutils < 1.28.7. will do that in next fb release
			Launch.classLoader.registerTransformer(ConfigAnytimeClassTransformer.class.getName());
			Launch.classLoader.registerTransformer(ZenUtilsShareTransformer.class.getName());
		}
		if(FermiumJarScanner.isModPresent("distanthorizons"))
			Launch.classLoader.registerTransformer(DistantHorizonsMixinConnectorTransformer.class.getName());

		FermiumRegistryAPI.enqueueMixin(false, "mixins.nischitweaker.dependencies.json", () -> !ConfigHandler.modContainers.removedDependencies.isEmpty());
	}

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[]{
				EnchanterToolsClassTransformer.class.getName()
		};
	}
	
	@Override
	public String getModContainerClass()
	{
		return null;
	}
	
	@Override
	public String getSetupClass()
	{
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) {
		if (Boolean.FALSE.equals(data.get("runtimeDeobfuscationEnabled"))) {
			MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
			CoreModManager.getReparseableCoremods().removeIf(s -> StringUtils.containsIgnoreCase(s, "fermiumbooter"));
		}
	}
	
	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}