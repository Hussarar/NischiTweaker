package nischitweaker.mixins.levelup2;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import levelup2.skills.combat.StealthLib;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// reported by wotmanar-jpg
@Mixin(StealthLib.class)
public abstract class StealthLibMixin {
    @WrapOperation(
            method = "entityHasVisionOf",
            at = @At(value = "INVOKE", target = "Llevelup2/skills/combat/StealthLib;entityIsFacing(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/EntityLivingBase;)Z")
    )
    private static boolean nischiTweaker_fixArgumentOrder(EntityLivingBase player, EntityLivingBase living, Operation<Boolean> original) {
        return original.call(living, player);
    }
}
