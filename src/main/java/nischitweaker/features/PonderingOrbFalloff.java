package nischitweaker.features;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber
public class PonderingOrbFalloff {

    private static final HashMap<UUID, Integer> playerUsageCount = new HashMap<>();

    public static int getUsageCount(UUID playerID) {
        return playerUsageCount.getOrDefault(playerID, 0);
    }

    public static void incrementUsageCount(UUID playerID) {
        playerUsageCount.compute(playerID, (k, v) -> v == null ? 1 : v + 1);
    }

    public static void decayCounters() {
        playerUsageCount.entrySet().forEach(e -> e.setValue(e.getValue() - 1));
        playerUsageCount.entrySet().removeIf(e -> e.getValue() <= 0);
    }

    public static void clearAll() {
        playerUsageCount.clear();
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        // Decay counters every 2000 ticks (100 seconds)
        if (DimensionManager.getWorld(0).getTotalWorldTime() % 2000 == 0)
            decayCounters();
    }

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {
        // Clear when overworld unloads to prevent data leaking between worlds in singleplayer
        if (!event.getWorld().isRemote && event.getWorld().provider.getDimension() == 0)
            clearAll();
    }
}
