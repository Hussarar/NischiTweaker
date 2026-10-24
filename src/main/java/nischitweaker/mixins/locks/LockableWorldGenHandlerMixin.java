package nischitweaker.mixins.locks;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import melonslise.locks.common.capability.LockableWorldGenHandler;
import melonslise.locks.mixin.TileEntityLockableLootAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LockableWorldGenHandler.class)
public abstract class LockableWorldGenHandlerMixin {

    @WrapOperation(
            method = "tryGeneratingLocks",
            at = @At(value = "INVOKE", target = "Lmelonslise/locks/mixin/TileEntityLockableLootAccessor;getItems()Lnet/minecraft/util/NonNullList;"),
            remap = false
    )
    private NonNullList<ItemStack> nischitweaker_fixDevGetItems(TileEntityLockableLootAccessor instance, Operation<NonNullList<ItemStack>> original) {
        return ((TileEntityLockableLootInvoker) instance).nischitweaker_getItems();
    }
}
