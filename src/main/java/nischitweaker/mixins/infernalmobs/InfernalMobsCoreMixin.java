package nischitweaker.mixins.infernalmobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import srpmixins.util.customphasemechanics.SRPSaveDataInterface;

@Mixin(InfernalMobsCore.class)
public abstract class InfernalMobsCoreMixin {

    /**
     * Modify infernal spawn chance for parasites in phase 9+
     */
    @ModifyExpressionValue(
            method = "processEntitySpawn",
            at = @At(value = "FIELD", target = "Latomicstryker/infernalmobs/common/InfernalMobsCore;eliteRarity:I"),
            remap = false
    )
    private int nischitweaker_modifyInfernalChance(int original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (!(entity instanceof EntityParasiteBase)) return original;

        World world = entity.world;
        if (world.provider.getDimension() != 111) return original;

        EntityPlayer player = world.getClosestPlayerToEntity(entity, 128);
        if (player == null) return original;

        int phase = SRPSaveDataInterface.get(world, player, null).getEvolutionPhase(world.provider.getDimension());
        if (phase < 9) return original; // below phase 9: no increase

        return (int) (original / (1. + ConfigHandler.infernalmobs.infernalParasiteChanceIncrease));
    }
}
