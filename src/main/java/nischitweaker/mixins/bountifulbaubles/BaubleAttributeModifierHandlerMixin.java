package nischitweaker.mixins.bountifulbaubles;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import cursedflames.bountifulbaubles.baubleeffect.BaubleAttributeModifierHandler;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BaubleAttributeModifierHandler.class)
public abstract class BaubleAttributeModifierHandlerMixin {

    @WrapWithCondition(
            method = "onPlayerCraft",
            at = @At(value = "INVOKE", target = "Lcursedflames/bountifulbaubles/baubleeffect/EnumBaubleModifier;generateModifier(Lnet/minecraft/item/ItemStack;)V"),
            remap = false
    )
    private static boolean nischitweaker_onlyGenerateModifierIfMissing(ItemStack stack) {
        // Only generate a modifier if the item doesn't already have one
        return !(stack.hasTagCompound() && stack.getTagCompound().hasKey("baubleModifier"));
    }
}
