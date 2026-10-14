package nischitweaker.mixins.suikecherry;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import suike.suikecherry.world.biome.BiomeRTG;

@Mixin(BiomeRTG.class)
public abstract class BiomeRTGMixin {

    @WrapOperation(
            method = "baseBiomeResLoc",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome;getBiomeName()Ljava/lang/String;")
    )
    private String nischitweaker_fixRTGCompat(Biome biome, Operation<String> original) {
        return "suikecherry:cherry_grove"; // avoids calling clientside-only getBiomeName
    }
}
