package nischitweaker.mixins.enchantertools;

import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin_MinEnchLvl {
    @ModifyArg(
            method = "calcItemStackEnchantability",
            at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I")
    )
    private static int nischitweaker_fixNegativeLvlCrash(int original){
        return Math.max(1, original);
    }
}
