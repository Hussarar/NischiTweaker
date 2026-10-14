package nischitweaker.mixins.reskillable;

import codersafterdark.reskillable.skill.magic.TraitGoldenOsmosis;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TraitGoldenOsmosis.class)
public abstract class TraitGoldenOsmosisMixin {

    @ModifyExpressionValue(
            method = "tryRepair",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getIsRepairable(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z")
    )
    private boolean nischitweaker_moreOsmosisItems(boolean original, @Local(name = "item") Item item) {
        if (original) return true;
        ResourceLocation loc = item.getRegistryName();
        return loc != null && ConfigHandler.reskillable.goldenOsmosisRepairableItems.contains(loc.toString());
    }
}
