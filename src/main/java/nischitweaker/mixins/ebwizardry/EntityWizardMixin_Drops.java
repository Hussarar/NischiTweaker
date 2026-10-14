package nischitweaker.mixins.ebwizardry;

import electroblob.wizardry.entity.living.EntityWizard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityWizard.class)
public abstract class EntityWizardMixin_Drops {

    @ModifyArg(
            method = "onInitialSpawn",
            at = @At(value = "INVOKE", target = "Lelectroblob/wizardry/entity/living/EntityWizard;setDropChance(Lnet/minecraft/inventory/EntityEquipmentSlot;F)V")
    )
    private float nischitweaker_noArmorWandDroppies(float chance) {
        return -10000.0F; // Effectively disable drops
    }
}
