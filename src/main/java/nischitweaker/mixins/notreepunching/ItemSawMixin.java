package nischitweaker.mixins.notreepunching;

import com.alcatrazescapee.notreepunching.common.items.ItemSaw;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemSaw.class)
public abstract class ItemSawMixin {

    @WrapOperation(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lcom/alcatrazescapee/notreepunching/common/items/ItemSaw;setNoRepair()Lnet/minecraft/item/Item;"),
            remap = false
    )
    private Item nischitweaker_makeRepairable(ItemSaw item, Operation<Item> original) {
        return item;
    }
}
