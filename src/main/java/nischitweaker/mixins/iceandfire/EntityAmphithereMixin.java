package nischitweaker.mixins.iceandfire;

import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityAmphithere.class)
public abstract class EntityAmphithereMixin {

    /**
     * Post AnimalTameEvent when amphithere is tamed
     */
    @WrapOperation(
            method = "onLivingUpdate",
            at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;setTamedBy(Lnet/minecraft/entity/player/EntityPlayer;)V")
    )
    private void nischitweaker_postTameEvent(EntityAmphithere amphithere, EntityPlayer player, Operation<Void> original) {
        if (!ForgeEventFactory.onAnimalTame(amphithere, player)) original.call(amphithere, player);
    }
}
