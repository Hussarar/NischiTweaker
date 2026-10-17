package nischitweaker.mixins.iceandfire;

import com.github.alexthe666.iceandfire.entity.EntityCockatrice;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// By fresh-Glitch
@Mixin(EntityCockatrice.class)
public abstract class EntityCockatriceMixin {

    @WrapOperation(
            method = "onLivingUpdate",
            at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityCockatrice;setTamedBy(Lnet/minecraft/entity/player/EntityPlayer;)V")
    )
    private void nischitweaker_postTameEvent(EntityCockatrice cockatrice, EntityPlayer player, Operation<Void> original) {
        if (!ForgeEventFactory.onAnimalTame(cockatrice, player)) original.call(cockatrice, player);
    }
}
