package nischitweaker.mixins.locks;

import melonslise.locks.common.container.LockPickingContainer;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LockPickingContainer.class)
public abstract class LockPickingContainerMixin {

    @ModifyConstant(method = "checkPin", constant = @Constant(floatValue = 1.25F), remap = false)
    private float nischitweaker_modifyCloseToFailPitchMulti(float original) {
        return ConfigHandler.locks.lockPickingPitchMultiplier;
    }
}
