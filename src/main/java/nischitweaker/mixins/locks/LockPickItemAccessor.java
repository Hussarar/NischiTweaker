package nischitweaker.mixins.locks;

import melonslise.locks.common.item.LockPickItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LockPickItem.class)
public interface LockPickItemAccessor {
    @Accessor(remap = false) @Mutable
    void setStrength(float strength);
}
