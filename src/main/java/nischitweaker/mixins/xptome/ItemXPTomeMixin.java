package nischitweaker.mixins.xptome;

import bl4ckscor3.mod.xptome.ItemXPTome;
import bl4ckscor3.mod.xptome.XPTome;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.math.MathHelper;
import nischitweaker.features.GreaterXPTome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

// Trick to use NBT instead of itemDamage by fresh-Glitch
@Mixin(ItemXPTome.class)
public abstract class ItemXPTomeMixin extends Item {

    @Unique
    private boolean nischitweaker$isNormalTome() {
        return XPTome.XP_BOOK == this;
    }

    @ModifyExpressionValue(
            method = "getXPStored",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItemDamage()I")
    )
    private int nischitweaker_readStored(int original, ItemStack stack) {
        if (nischitweaker$isNormalTome()) return original;

        // Use centralized NBT reading method
        int storedXP = GreaterXPTome.getStoredXP(stack);
        // The caller computes MAX_STORAGE minus this, so we need to account for it
        return getMaxDamage() - storedXP;
    }

    @WrapOperation(
            method = "setStoredXP",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setItemDamage(I)V")
    )
    private void nischitweaker_writeStored(ItemStack stack, int damage, Operation<Void> original) {
        if (nischitweaker$isNormalTome()) {
            original.call(stack, damage);
            return;
        }

        int max = getMaxDamage();
        int stored = MathHelper.clamp(max - damage, 0, max);
        stack.setTagInfo(GreaterXPTome.STORED_KEY, new NBTTagInt(stored));

        // Delete old stored val by setting damage to maxdamage = 0 dura left = empty
        original.call(stack, max);
    }

    @ModifyConstant(
            method = {"onItemRightClick", "addInformation"},
            constant = @Constant(intValue = 1395)
    )
    private int nischitweaker_modifyMaxStorage_remap(int original) {
        return getMaxDamage();
    }

    @ModifyConstant(
            method = {"addXP", "setStoredXP", "getXPStored"},
            constant = @Constant(intValue = 1395),
            remap = false
    )
    private int nischitweaker_modifyMaxStorage(int original) {
        return getMaxDamage();
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if (nischitweaker$isNormalTome()) return super.showDurabilityBar(stack);
        return ((ItemXPTome) (Object) this).getXPStored(stack) < getMaxDamage();
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (nischitweaker$isNormalTome()) return super.getDurabilityForDisplay(stack);
        int max = getMaxDamage();
        if (max <= 0) return 0.0;
        return 1.0 - (((ItemXPTome) (Object) this).getXPStored(stack) / (double) max);
    }
}
