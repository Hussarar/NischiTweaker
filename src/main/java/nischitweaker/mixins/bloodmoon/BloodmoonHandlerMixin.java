package nischitweaker.mixins.bloodmoon;

import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import lumien.bloodmoon.server.BloodmoonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import srpmixins.util.customphasemechanics.SRPSaveDataInterface;

import java.util.List;

@Mixin(BloodmoonHandler.class)
public abstract class BloodmoonHandlerMixin {

    /**
     * Disable bloodmoon if all players are below the configured evolution phase
     */
    @Inject(
            method = "endWorldTick",
            at = @At(value = "INVOKE", target = "Llumien/bloodmoon/server/BloodmoonHandler;setBloodmoon(Z)V", ordinal = 1),
            cancellable = true,
            remap = false
    )
    private void nischitweaker_disableBloodmoon(TickEvent.WorldTickEvent event, CallbackInfo ci) {
        if (!SRPConfigSystems.useEvolution) return; // for unparasited

        World world = event.world;
        List<EntityPlayer> players = world.playerEntities;
        if (players.isEmpty()) return; // no players in dim, shouldn't happen

        // anyone above configured phase: bloodmoons allowed
        if (players.stream().anyMatch(player ->
                SRPSaveDataInterface.get(world, player, null).getEvolutionPhase(world.provider.getDimension()) >= ConfigHandler.bloodmoon.bloodmoonMinPhase
        )) return;

        ci.cancel(); // no bloodmoon if everyone online below configured phase
    }
}
