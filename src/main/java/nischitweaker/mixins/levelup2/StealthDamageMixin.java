package nischitweaker.mixins.levelup2;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import levelup2.skills.combat.StealthDamage;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// reported by wotmanar-jpg
@Mixin(StealthDamage.class)
public abstract class StealthDamageMixin {
    @WrapOperation(
            method = "onDamage",
            at = @At(value = "INVOKE", target = "Llevelup2/skills/combat/StealthLib;canSeePlayer(Lnet/minecraft/entity/EntityLivingBase;)Z"),
            remap = false
    )
    private static boolean nischitweaker_removeRedundantCheck(EntityLivingBase target, Operation<Boolean> original, @Local(name = "player") EntityPlayer player) {
        // additional check whether the mob is already targeting the player
        if(target.getRevengeTarget() == player) return true;
        else if(target instanceof EntityLiving && ((EntityLiving) target).getAttackTarget() == player) return true;
        else return false; // with the fixed call in StealthLibMixin the boolean logic makes this call not matter at all
    }
}
