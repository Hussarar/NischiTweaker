package nischitweaker.mixins.ebwizardry;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import electroblob.wizardry.integration.jei.ImbuementAltarRecipeCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;
import java.util.List;

@Mixin(ImbuementAltarRecipeCategory.class)
public abstract class ImbuementAltarRecipeCategoryMixin {

    /**
     * Don't register armor JEI recipes for imbuement altar
     */
    @WrapOperation(
            method = "generateRecipes",
            at = @At(value = "INVOKE", ordinal = 3, target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z"),
            remap = false
    )
    private static boolean nischitweaker_dontRegisterArmorJeiRecipes(List<?> recipes, Collection<?> armorRecipes, Operation<Boolean> original) {
        return false;
    }
}
