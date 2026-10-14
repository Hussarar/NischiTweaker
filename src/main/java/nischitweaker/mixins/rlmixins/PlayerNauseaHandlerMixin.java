package nischitweaker.mixins.rlmixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import rlmixins.handlers.rlmixins.PlayerNauseaHandler;

@Mixin(PlayerNauseaHandler.class)
public abstract class PlayerNauseaHandlerMixin {

    @ModifyConstant(
            method = "onInputUpdateEvent",
            constant = {@Constant(floatValue = 4.0F), @Constant(floatValue = 2.0F)},
            remap = false
    )
    private static float nischitweaker_reduceMovementMult(float mult) {
        return mult / 2;
    }
}
