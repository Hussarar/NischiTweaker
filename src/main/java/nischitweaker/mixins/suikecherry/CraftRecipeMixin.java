package nischitweaker.mixins.suikecherry;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Mixin;
import suike.suikecherry.recipe.CraftRecipe;

@Mixin(CraftRecipe.class)
public abstract class CraftRecipeMixin {

    @WrapMethod(method = "register", remap = false)
    private static void nischitweaker_dontModifyWoodRecipes(Operation<Void> original) {
        // no op
    }
}
