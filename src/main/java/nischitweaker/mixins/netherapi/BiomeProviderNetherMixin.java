package nischitweaker.mixins.netherapi;

import git.jbredwards.nether_api.mod.common.world.biome.BiomeProviderNether;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BiomeProviderNether.class)
public abstract class BiomeProviderNetherMixin {

    @ModifyConstant(method = "getBiomeGenerators", constant = @Constant(intValue = 3), remap = false)
    private int nischitweaker_changeNetherBiomeSize(int original) {
        return ConfigHandler.netherapi.netherBiomeSize;
    }
}
