package nischitweaker.mixins.vanilla;

import net.minecraft.enchantment.EnchantmentFrostWalker;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnchantmentFrostWalker.class)
public abstract class EnchantmentFrostWalkerMixin {
    @ModifyVariable(method = "freezeNearby", at = @At(value = "STORE"))
    private static float nischitweaker_increaseFrostWalkerSize(float original){
        return ConfigHandler.vanilla.frostWalkerMulti * original;
    }
}
