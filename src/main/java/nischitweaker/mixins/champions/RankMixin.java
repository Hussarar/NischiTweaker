package nischitweaker.mixins.champions;

import c4.champions.common.rank.Rank;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Rank.class)
public abstract class RankMixin {

    /**
     * Modifies the damage scaling behavior for high-damage champions.
     * When base attack damage >= threshold, switches from multiplicative to flat damage scaling.
     */
    @ModifyExpressionValue(
            method = "applyGrowth(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/ai/attributes/IAttribute;DI)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/attributes/IAttributeInstance;getBaseValue()D", ordinal = 0)
    )
    private double nischitweaker_modifyHighDamageScaling(
            double baseDamage,
            @Local(argsOnly = true) IAttribute attribute,
            @Local(argsOnly = true) LocalDoubleRef amountRef,
            @Local(argsOnly = true) LocalIntRef operationRef
    ) {
        if (attribute != SharedMonsterAttributes.ATTACK_DAMAGE) return baseDamage;
        if (baseDamage * amountRef.get() < ConfigHandler.champions.flatDamageAmount) return baseDamage;

        // Set operation to 0 (addition) and amount to configured flat damage (will be multiplied by growthFactor)
        operationRef.set(0);
        amountRef.set(ConfigHandler.champions.flatDamageAmount);

        // Don't modify base damage, modification is handled by champions
        return baseDamage;
    }
}