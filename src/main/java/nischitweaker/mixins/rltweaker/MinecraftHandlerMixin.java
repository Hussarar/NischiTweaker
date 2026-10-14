package nischitweaker.mixins.rltweaker;

import com.charles445.rltweaker.handler.MinecraftHandler;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.sirsquidly.oe.entity.EntityDrowned;
import net.minecraft.entity.monster.EntityZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftHandler.class)
public abstract class MinecraftHandlerMixin {

    @WrapWithCondition(
            method = "onEntityJoinWorldEvent",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/EntityZombie;setBreakDoorsAItask(Z)V")
    )
    private boolean nischitweaker_dontLogBs(EntityZombie zombie, boolean enabled) {
        return !(zombie instanceof EntityDrowned);
    }
}
