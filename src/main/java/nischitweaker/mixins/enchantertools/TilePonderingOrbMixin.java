package nischitweaker.mixins.enchantertools;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.sirsquidly.enchanter_tools.common.blocks.tileentity.TilePonderingOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import nischitweaker.features.PonderingOrbFalloff;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TilePonderingOrb.class)
public abstract class TilePonderingOrbMixin extends TileEntity {

    @ModifyExpressionValue(
            method = "update",
            at = @At(value = "INVOKE", target = "Lcom/sirsquidly/enchanter_tools/common/blocks/tileentity/TilePonderingOrb;playerIsPonderingOrb(Lnet/minecraft/entity/player/EntityPlayer;)Z", remap = false)
    )
    private boolean nischitweaker_applyFalloff(boolean original, @Local(name = "player") EntityPlayer player) {
        if (this.world.isRemote) return original; // only modify serverside
        if (!original) return false; // Player is not looking at the orb

        PonderingOrbFalloff.incrementUsageCount(player.getUniqueID());
        int count = PonderingOrbFalloff.getUsageCount(player.getUniqueID());

        if (this.world.rand.nextInt(200) < count) {
            if (count > 200) {
                Potion fear = Potion.getPotionFromResourceLocation("lycanitesmobs:fear");
                if (fear != null) {
                    player.addPotionEffect(new PotionEffect(fear, 400, 0));
                }
            }

            if (count > 100) player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 400, 2));
            else if (count > 50) player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 400, 1));
            else if (count > 25) player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 400, 0));

            //Don't give xp
            return false;
        }

        return true;
    }
}
