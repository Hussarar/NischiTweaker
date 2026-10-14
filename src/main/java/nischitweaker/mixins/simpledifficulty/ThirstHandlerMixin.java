package nischitweaker.mixins.simpledifficulty;

import com.charles445.simpledifficulty.config.ModConfig;
import com.charles445.simpledifficulty.handler.ThirstHandler;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThirstHandler.class)
public abstract class ThirstHandlerMixin {
    @Shadow(remap = false) protected abstract boolean shouldSkipThirst(EntityPlayer player);
    @Shadow(remap = false) protected abstract void addExhaustion(EntityPlayer player, float exhaustion);

    @WrapMethod(method = "onAttackEntity", remap = false)
    private void nischitweaker_replaceThirstCheck(AttackEntityEvent event, Operation<Void> original) {
        //no op
    }

    @Inject(
            method = "onLivingHurt",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/event/entity/living/LivingHurtEvent;getEntity()Lnet/minecraft/entity/Entity;", ordinal = 1),
            remap = false
    )
    private void nischitweaker_thirstAfterIFrames(LivingHurtEvent event, CallbackInfo ci) {
        DamageSource source = event.getSource();
        if (source == null) return;

        if (!(source.getTrueSource() instanceof EntityPlayer)) return;
        if (source.getImmediateSource() instanceof EntityLivingBase &&
                !(source.getImmediateSource() instanceof EntityPlayer)) return; // pets live and do my bidding

        EntityPlayer player = (EntityPlayer) source.getTrueSource();
        if (!this.shouldSkipThirst(player))
            this.addExhaustion(player, (float) ModConfig.server.thirst.thirstAttacking);
    }
}
