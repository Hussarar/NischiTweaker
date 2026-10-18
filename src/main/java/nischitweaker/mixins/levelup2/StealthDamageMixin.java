package nischitweaker.mixins.levelup2;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import levelup2.skills.combat.StealthDamage;
import net.minecraft.entity.EntityLivingBase;
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
    private static boolean nischitweaker_removeRedundantCheck(EntityLivingBase entity, Operation<Boolean> original) {
        return false; // with the fixed call in StealthLibMixin the boolean logic makes this call not matter at all
    }
}
