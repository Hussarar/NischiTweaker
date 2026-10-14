package nischitweaker.mixins.suikecherry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rtg.api.RTGAPI;
import rtg.api.world.biome.IRealisticBiome;
import suike.suikecherry.world.biome.ModBiomes;

@Mixin(ModBiomes.class)
public abstract class ModBiomesMixin {

    @Redirect(
            method = "initBiomes",
            at = @At(value = "INVOKE", target = "Lrtg/api/RTGAPI;addRTGBiomes([Lrtg/api/world/biome/IRealisticBiome;)V"),
            remap = false
    )
    private static void nischitweaker_fixRTGCompat(IRealisticBiome[] biomes) {
        RTGAPI.RTG_BIOMES.addBiomes(biomes);
    }
}
