package nischitweaker.mixins.sereneseasons;

import com.charles445.simpledifficulty.compat.mod.SereneSeasonsModifier;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import sereneseasons.config.SeasonsConfig;

@Mixin(SereneSeasonsModifier.class)
public abstract class SereneSeasonsModifierMixin {

    @ModifyExpressionValue(
            method = "getWorldInfluence",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;isSurfaceWorld()Z")
    )
    private boolean nischitweaker_fixMPTemperature(boolean original, World world) {
        return original && SeasonsConfig.isDimensionWhitelisted(world.provider.getDimension());
    }
}
