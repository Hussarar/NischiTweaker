package nischitweaker.mixins.suikecherry;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import suike.suikecherry.data.TreasureData;

import java.util.Collection;

@Mixin(TreasureData.class)
public abstract class TreasureDataMixin {

    @WrapOperation(
            method = "createTreasureList",
            at = @At(value = "INVOKE", target = "Ljava/util/Collections;addAll(Ljava/util/Collection;[Ljava/lang/Object;)Z"),
            remap = false
    )
    private static boolean nischitweaker_disableDefaultLoot(Collection<?> c, Object[] elements, Operation<Boolean> original) {
        return false; // don't add default loot, done via cfg file instead
    }
}
