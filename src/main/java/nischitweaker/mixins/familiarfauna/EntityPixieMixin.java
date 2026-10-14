package nischitweaker.mixins.familiarfauna;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import familiarfauna.entities.EntityPixie;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityPixie.class)
public abstract class EntityPixieMixin {

    /**
     * Modify pixie spawning max height
     */
    @ModifyExpressionValue(
            method = "getCanSpawnHere",
            at = @At(value = "CONSTANT", args = "intValue=90"),
            remap = false
    )
    private int nischitweaker_modifyPixieSpawningMaxHeight(int original) {
        return ConfigHandler.familiarfauna.pixieMaxSpawnHeight;
    }
}
