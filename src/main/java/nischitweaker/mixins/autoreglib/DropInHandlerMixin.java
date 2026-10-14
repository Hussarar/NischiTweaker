package nischitweaker.mixins.autoreglib;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vazkii.arl.util.DropInHandler;

@Mixin(DropInHandler.class)
public abstract class DropInHandlerMixin {

    @WrapOperation(
            method = "onRightClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Slot;getSlotIndex()I"),
            remap = false
    )
    private static int nischitweaker_fixQuarkShulkerDropoff(Slot slot, Operation<Integer> original) {
        return slot.slotNumber; // slotNumber is correct
    }
}
