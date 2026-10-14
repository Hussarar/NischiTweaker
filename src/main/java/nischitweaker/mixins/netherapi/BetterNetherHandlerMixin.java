package nischitweaker.mixins.netherapi;

import git.jbredwards.nether_api.mod.common.compat.betternether.BetterNetherHandler;
import git.jbredwards.nether_api.mod.common.compat.betternether.BiomeBetterNether;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import paulevs.betternether.biomes.BiomeRegister;

@Mixin(BetterNetherHandler.class)
public abstract class BetterNetherHandlerMixin {

    @ModifyConstant(method = "getWeight", constant = @Constant(intValue = 1), remap = false)
    private static int nischitweaker_changeNetherBiomeWeights(int original, BiomeBetterNether biome) {
        return biome.netherBiome == BiomeRegister.BIOME_BONE_REEF || biome.netherBiome == BiomeRegister.BIOME_GRASSLANDS ? 3 : original;
        // in legacy BetterNether all 5 normal (not edge, not sub) biomes have weight 1
        // those 5 normal ones are Gravel Desert, Nether Jungle, Wart Forest, Nether Grasslands, Nether Mushroom Forest
    }
}
