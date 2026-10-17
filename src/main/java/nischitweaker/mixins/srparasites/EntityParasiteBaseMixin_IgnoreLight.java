package nischitweaker.mixins.srparasites;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityParasiteBase.class)
public abstract class EntityParasiteBaseMixin_IgnoreLight extends Entity {
    public EntityParasiteBaseMixin_IgnoreLight(World worldIn) {
        super(worldIn);
    }

    /**
     * Skip light level check for parasites in The End
     */
    @ModifyExpressionValue(
            method = "getCanSpawnHere",
            at = @At(value = "INVOKE", target = "Lcom/dhanantry/scapeandrunparasites/entity/ai/misc/EntityParasiteBase;isValidLightLevelOne()Z", remap = false)
    )
    private boolean nischitweaker_skipLightCheck(boolean original) {
        if (original) return original;
        if (this.world.provider.getDimension() == 1) return true; // Allow spawning in The End
        return original;
    }
}
