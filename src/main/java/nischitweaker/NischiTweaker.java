package nischitweaker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nischitweaker.features.GreaterXPTome;

@Mod(
        modid = Tags.MODID,
        version = Tags.VERSION,
        name = Tags.NAME,
        dependencies =
                "required-after:fermiumbooter@[1.5.3,);" +
                "required:betterconfig@[1.2.0,)",
        acceptableRemoteVersions = "*"
)
public class NischiTweaker {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(Loader.isModLoaded("xpbook")) MinecraftForge.EVENT_BUS.register(GreaterXPTome.class);
    }
}