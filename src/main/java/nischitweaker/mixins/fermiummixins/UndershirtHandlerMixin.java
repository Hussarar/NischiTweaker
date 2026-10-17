package nischitweaker.mixins.fermiummixins;

import fermiummixins.handlers.reskillable.UndershirtHandler;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

// by cdstk
@Mixin(UndershirtHandler.class)
public abstract class UndershirtHandlerMixin {

    /**
     * Configure undershirt max damage
     */
    @ModifyConstant(
            method = "onFirstAidLivingDamageHigh",
            constant = @Constant(floatValue = 1000.0F),
            remap = false
    )
    private static float nischitweaker_undershirtMaxDamage(float constant) {
        double maxDamage = ConfigHandler.fermiummixins.undershirtMaxDamage;
        if (maxDamage < 0) return Float.POSITIVE_INFINITY;
        return (float) maxDamage;
    }

    /**
     * Configure undershirt min health
     */
    @ModifyConstant(
            method = "onFirstAidLivingDamageHigh",
            constant = @Constant(doubleValue = 4.0),
            remap = false
    )
    private static double nischitweaker_undershirtMinHealth(double constant) {
        return ConfigHandler.fermiummixins.undershirtMinHealth;
    }
}
