package nischitweaker.mixins.locks;

import melonslise.locks.common.item.LockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LockItem.class)
public interface LockItemAccessor {
    @Accessor(remap = false) @Mutable
    void setLength(int length);

    @Accessor(remap = false) @Mutable
    void setEnchantmentValue(int enchantmentValue);

    @Accessor(remap = false) @Mutable
    void setResistance(float resistance);
}
