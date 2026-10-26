package nischitweaker.mixins.globalgamerules;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import se.gory_moon.globalgamerules.WorldEvents;

@Mixin(WorldEvents.class)
public abstract class WorldEventsMixin_ServerOverworldOnly {

    /**
     * All dimensions share the overworld's gamerules (DerivedWorldInfo), so reapplying the config on every dimension load
     * reverts command changes, and client worlds unloading write stale gamerules back into the config
     */
    @WrapMethod(method = "onWorldLoad", remap = false)
    private static void nischitweaker_onlyLoadServerOverworld(WorldEvent.Load event, Operation<Void> original) {
        if (nischitweaker_isServerOverworld(event.getWorld())) original.call(event);
    }

    @WrapMethod(method = "onWorldUnLoad", remap = false)
    private static void nischitweaker_onlyUnloadServerOverworld(WorldEvent.Unload event, Operation<Void> original) {
        if (nischitweaker_isServerOverworld(event.getWorld())) original.call(event);
    }

    @Unique
    private static boolean nischitweaker_isServerOverworld(World world) {
        return !world.isRemote && world.provider.getDimension() == 0;
    }
}
