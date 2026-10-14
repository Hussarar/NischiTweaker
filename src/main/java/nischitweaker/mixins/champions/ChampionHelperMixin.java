package nischitweaker.mixins.champions;

import c4.champions.common.util.ChampionHelper;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import srpmixins.util.customphasemechanics.SRPSaveDataInterface;

@Mixin(ChampionHelper.class)
public abstract class ChampionHelperMixin {

    /**
     * Modify champion spawn chance for parasites in phase 9+
     */
    @ModifyExpressionValue(
            method = "generateRank",
            at = @At(value = "INVOKE", ordinal = 0, target = "Lc4/champions/common/rank/Rank;getChance()F"),
            remap = false
    )
    private static float nischitweaker_modifyChampionChance(float original, @Local(argsOnly = true) EntityLiving entity) {
        if (!(entity instanceof EntityParasiteBase)) return original;

        World world = entity.world;
        if (world.provider.getDimension() != 111) return original;

        EntityPlayer player = world.getClosestPlayerToEntity(entity, 128);
        if (player == null) return original;

        int phase = SRPSaveDataInterface.get(world, player, null).getEvolutionPhase(world.provider.getDimension());
        if (phase < 9) return original; // below phase 9: no increase

        return original * (1 + (float) ConfigHandler.champions.championParasiteChanceIncrease);
    }
}
