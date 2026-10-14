package nischitweaker.mixins.enchantertools;

import com.llamalad7.mixinextras.sugar.Local;
import com.sirsquidly.enchanter_tools.common.PotionComprehension;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PotionComprehension.class)
public abstract class PotionComprehensionMixin {
    @ModifyVariable(
            method = {"onExperiencingDeath", "onPickupXp"},
            at = @At("STORE"),
            name = "multiplier",
            remap = false
    )
    private static float nischitweaker_modifyComprehensionMulti(float multiplier, @Local(name = "amplifier") int amplifier){
        return 1 + ConfigHandler.enchanterTools.comprehensionScale * (amplifier + 1);
    }
}
