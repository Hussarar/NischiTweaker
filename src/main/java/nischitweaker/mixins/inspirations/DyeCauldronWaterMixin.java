package nischitweaker.mixins.inspirations;

import knightminer.inspirations.library.recipe.cauldron.ICauldronRecipe;
import knightminer.inspirations.recipes.recipe.DyeCauldronWater;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DyeCauldronWater.class)
public abstract class DyeCauldronWaterMixin {

    @Inject(method = "matches", at = @At("HEAD"), cancellable = true, remap = false)
    private void nischitweaker_onlyDyeWhenNotCooking(ItemStack stack, boolean boiling, int level, ICauldronRecipe.CauldronState state, CallbackInfoReturnable<Boolean> cir) {
        if (boiling) cir.setReturnValue(false); // no matches to protect coffee
    }
}
