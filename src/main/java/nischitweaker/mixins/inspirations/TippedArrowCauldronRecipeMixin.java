package nischitweaker.mixins.inspirations;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import knightminer.inspirations.library.recipe.cauldron.ICauldronRecipe;
import knightminer.inspirations.recipes.recipe.TippedArrowCauldronRecipe;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TippedArrowCauldronRecipe.class)
public abstract class TippedArrowCauldronRecipeMixin {

    @ModifyReturnValue(method = "matches", at = @At("RETURN"), remap = false)
    private boolean nischitweaker_fixCauldronSplashingArrows(boolean original, @Local(argsOnly = true) ICauldronRecipe.CauldronState state) {
        if (!original) return false;

        PotionType potion = state.getPotion();

        return potion != PotionTypes.EMPTY
                && potion != PotionTypes.WATER
                && potion != PotionTypes.MUNDANE
                && potion != PotionTypes.THICK
                && potion != PotionTypes.AWKWARD;
    }
}
