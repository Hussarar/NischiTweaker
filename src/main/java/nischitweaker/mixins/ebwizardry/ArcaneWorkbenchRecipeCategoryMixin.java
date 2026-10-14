package nischitweaker.mixins.ebwizardry;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import electroblob.wizardry.integration.jei.ArcaneWorkbenchRecipeCategory;
import electroblob.wizardry.item.ItemArmourUpgrade;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;
import java.util.List;

@Mixin(ArcaneWorkbenchRecipeCategory.class)
public abstract class ArcaneWorkbenchRecipeCategoryMixin {

    @WrapOperation(
            method = "generateRecipes",
            at = @At(value = "INVOKE", ordinal = 2, target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z"),
            remap = false
    )
    private static boolean nischitweaker_dontRegisterScrollJeiRecipes(List<?> recipes, Collection<?> scrollRecipes, Operation<Boolean> original) {
        return false;
    }

    @Definition(id = "ItemArmourUpgrade", type = ItemArmourUpgrade.class)
    @Definition(id = "item", local = @Local(type = Item.class, name = "item"))
    @Expression("item instanceof ItemArmourUpgrade")
    @ModifyExpressionValue(
            method = "generateUpgradeRecipes",
            at = @At("MIXINEXTRAS:EXPRESSION"),
            remap = false
    )
    private static boolean nischitweaker_dontRegisterArmorUpgradeJeiRecipes(boolean original) {
        return false;
    }
}
