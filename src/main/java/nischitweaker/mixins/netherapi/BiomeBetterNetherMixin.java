package nischitweaker.mixins.netherapi;

import git.jbredwards.nether_api.mod.common.compat.betternether.BiomeBetterNether;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import paulevs.betternether.biomes.BiomeRegister;
import paulevs.betternether.biomes.NetherBiome;

@Mixin(BiomeBetterNether.class)
public abstract class BiomeBetterNetherMixin {

    @ModifyConstant(method = "lambda$getSubBiomes$1", constant = @Constant(intValue = 1000), remap = false)
    private int nischitweaker_changeBoneReefRarity(int original, NetherBiome netherBiome) {
        return netherBiome == BiomeRegister.BIOME_BONE_REEF ? 1 : original;
    }
}
