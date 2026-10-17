package nischitweaker.mixins.fermiummixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import fermiummixins.handlers.bountifulbaubles.BrokenHeartBaubleHandler;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

// by cdstk
@Mixin(BrokenHeartBaubleHandler.class)
public abstract class BrokenHeartHandlerMixin {

    /**
     * Configure broken heart max damage
     */
    @ModifyConstant(
            method = "onFirstAidLivingDamageLow",
            constant = @Constant(floatValue = 1000.0F),
            remap = false
    )
    private static float nischitweaker_brokenHeartMaxDamage(float constant) {
        double maxDamage = ConfigHandler.fermiummixins.brokenHeartMaxDamage;
        if (maxDamage < 0) return Float.POSITIVE_INFINITY;
        return (float) maxDamage;
    }

    /**
     * Configure broken heart min health
     */
    @ModifyConstant(
            method = "onFirstAidLivingDamageLow",
            constant = @Constant(intValue = 4),
            remap = false
    )
    private static int nischitweaker_brokenHeartMinHealth(int constant) {
        return ConfigHandler.fermiummixins.brokenHeartMinHealth;
    }

    /**
     * No leftover damage reduction (don't reduce player health by undistributed damage)
     */
    @ModifyExpressionValue(
            method = "onFirstAidLivingDamageLow",
            at = @At(value = "INVOKE", target = "Lichttt/mods/firstaid/api/event/FirstAidLivingDamageEvent;getUndistributedDamage()F", ordinal = 1),
            remap = false
    )
    private static float nischitweaker_brokenHeartNoLeftover(float original) {
        return 0.0F; // don't break my heart, i mean don't reduce player health by leftover (undistributed) dmg leading to kill anyway
    }

    /**
     * Always remove consistent amount (30% + 1 heart) regardless of parts count
     */
    @ModifyExpressionValue(
            method = "onFirstAidLivingDamageLow",
            at = @At(value = "INVOKE", target = "Ljava/util/List;size()I"),
            remap = false
    )
    private static int nischitweaker_brokenHeartNoPartsCount(int original) {
        return 1; // always remove 30% of health plus 1 heart. before it could be 30% + (1 OR 2 hearts depending on the situation)
    }
}
