package nischitweaker.mixins.ebwizardry;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import electroblob.wizardry.item.ItemWizardArmour;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemWizardArmour.class)
public abstract class ItemWizardArmourMixin {

    /**
     * Don't register wizard armor in mana flask charging
     */
    @WrapWithCondition(
            method = "<init>*",
            at = @At(value = "INVOKE", target = "Lelectroblob/wizardry/registry/WizardryRecipes;addToManaFlaskCharging(Lnet/minecraft/item/Item;)V"),
            remap = false
    )
    private static boolean nischitweaker_dontRegisterArmorJei(Item item) {
        return false; // Don't call original - skip registration
    }
}
