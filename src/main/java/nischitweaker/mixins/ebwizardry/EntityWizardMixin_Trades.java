package nischitweaker.mixins.ebwizardry;

import electroblob.wizardry.entity.living.EntityWizard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityWizard.class)
public abstract class EntityWizardMixin_Trades {

    /**
     * Remove wizard trade: apprentice tier armor and arcane tome
     */
    @ModifyArg(
            method = "getRandomItemOfTier",
            at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 6),
            remap = false
    )
    private int nischitweaker_removeWizardTrade_apprentice(int origMaxRoll) {
        return 6; // from 10, to skip the weight 2 of armor trades and 2 weight arcane tome
    }

    /**
     * Remove wizard trade: advanced tier wand upgrades and arcane tome
     */
    @ModifyArg(
            method = "getRandomItemOfTier",
            at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 15),
            remap = false
    )
    private int nischitweaker_removeWizardTrade_advanced(int origMaxRoll) {
        return 6; // from 12, to skip the weight 4 wand upgrades and the weight 2 arcane tome
    }

    /**
     * Remove wizard trade: master tier arcane tome
     */
    @ModifyArg(
            method = "getRandomItemOfTier",
            at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 22),
            remap = false
    )
    private int nischitweaker_removeWizardTrade_master1(int origMaxRoll) {
        return 6; // from 8, to skip the weight 2 arcane tome
    }

    /**
     * Remove wizard trade: master tier arcane tome (elementless)
     */
    @ModifyArg(
            method = "getRandomItemOfTier",
            at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 23),
            remap = false
    )
    private int nischitweaker_removeWizardTrade_master2(int origMaxRoll) {
        return 1; // from 3, to skip the weight 2 arcane tome. elementless master wizard trades will always be a neutral master wand
    }
}
