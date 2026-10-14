package nischitweaker.mixins.enchantertools;

import codersafterdark.reskillable.base.ExperienceHelper;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.sirsquidly.enchanter_tools.common.blocks.BlockPonderingOrb;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BlockPonderingOrb.class)
public abstract class BlockPonderingOrbMixin {

    /**
     * Drain XP points instead of levels
     */
    @ModifyArg(
            method = "onBlockActivated",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;onEnchant(Lnet/minecraft/item/ItemStack;I)V")
    )
    private int nischitweaker_drainXPnotLevels(int original, @Local(argsOnly = true) EntityPlayer player) {
        // Reduce xp
        ExperienceHelper.drainPlayerXP(player, original);
        // Don't reduce lvls
        return 0;
    }

    /**
     * Check total XP instead of experience level
     */
    @ModifyExpressionValue(
            method = "onBlockActivated",
            at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;experienceLevel:I")
    )
    private int nischitweaker_checkXPnotLevels(int original, @Local(argsOnly = true) EntityPlayer player) {
        return player.experienceTotal;
    }
}
