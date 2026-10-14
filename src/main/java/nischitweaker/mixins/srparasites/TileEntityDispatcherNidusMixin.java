package nischitweaker.mixins.srparasites;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityDod;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileEntityDod.class)
public abstract class TileEntityDispatcherNidusMixin extends TileEntity {

    /**
     * Dispatcher Nodes add pivot effect to nearby parasites if growing dispatcher fails
     */
    @Inject(
            method = "update", // update
            at = @At(value = "INVOKE", target = "Lcom/dhanantry/scapeandrunparasites/world/SRPSaveData;setTotalKills(IIZLnet/minecraft/world/World;Z)Z", remap = false)
    )
    private void nischitweaker_addPivotNearby(CallbackInfo ci, @Local AxisAlignedBB axisalignedbb) {
        for (EntityParasiteBase para : this.world.getEntitiesWithinAABB(EntityParasiteBase.class, axisalignedbb))
            SRPPotions.applyStackPotion(SRPPotions.PIVOT_E, para, 6000, 0);
    }
}
