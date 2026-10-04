package nischitweaker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(
        modid = Tags.MODID,
        version = Tags.VERSION,
        name = Tags.NAME,
        dependencies =
                "required-after:fermiumbooter@[1.3.2,);" +
                "required:betterconfig@[1.2.0,)",
        acceptableRemoteVersions = "*"
)
public class NischiTweaker {
    public static boolean completedLoading = false;

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        completedLoading = true;
    }
}