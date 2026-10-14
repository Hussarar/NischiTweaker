package nischitweaker.mixins.xptome;

import bl4ckscor3.mod.xptome.ItemXPTome;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.math.MathHelper;
import nischitweaker.config.ConfigHandler;
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
    private boolean nischitweaker$isGreaterTome() {
        return GreaterXPTome.GREATER_XP_TOME == (Object) this;
    }

    @Unique
    private int nischitweaker$getMaxStorage() {
        return nischitweaker$isGreaterTome() ? ConfigHandler.xptome.maxStorage : 1395;
    }

    @ModifyExpressionValue(
            method = "getXPStored",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItemDamage()I")
    )
    private int nischitweaker_readStored(int original, ItemStack stack) {
        if (!nischitweaker$isGreaterTome()) return original;

        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null && tag.hasKey(GreaterXPTome.STORED_KEY)) {
            // The caller computes MAX_STORAGE minus this, so we need to account for it
            return nischitweaker$getMaxStorage() - tag.getInteger(GreaterXPTome.STORED_KEY);
        }
        return original;
    }

    @WrapOperation(
            method = "setStoredXP",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setItemDamage(I)V")
    )
    private void nischitweaker_writeStored(ItemStack stack, int damage, Operation<Void> original) {
        if (!nischitweaker$isGreaterTome()) {
            original.call(stack, damage);
            return;
        }

        int max = nischitweaker$getMaxStorage();
        int stored = MathHelper.clamp(max - damage, 0, max);
        stack.setTagInfo(GreaterXPTome.STORED_KEY, new NBTTagInt(stored));

        // Delete old stored val by setting damage to maxdamage = 0 dura left = empty
        original.call(stack, max);
    }

    @ModifyConstant(
            method = {"onItemRightClick", "addInformation", "addXP", "setStoredXP", "getXPStored"},
            constant = @Constant(intValue = 1395),
            remap = false
    )
    private int nischitweaker_modifyMaxStorage(int original) {
        return nischitweaker$getMaxStorage();
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if (!nischitweaker$isGreaterTome()) return super.showDurabilityBar(stack);
        return ((ItemXPTome) (Object) this).getXPStored(stack) < nischitweaker$getMaxStorage();
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (!nischitweaker$isGreaterTome()) return super.getDurabilityForDisplay(stack);
        int max = nischitweaker$getMaxStorage();
        if (max <= 0) return 0.0;
        return 1.0 - (((ItemXPTome) (Object) this).getXPStored(stack) / (double) max);
    }
}
