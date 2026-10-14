package nischitweaker.mixins.enchantertools;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.sirsquidly.enchanter_tools.common.CommonEvents;
import net.minecraft.entity.EntityLivingBase;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CommonEvents.class)
public abstract class CommonEventsMixin {
    @WrapOperation(
            method = "onProjectileImpact",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;canBeHitWithPotion()Z"),
            remap = false
    )
    private static boolean nischitweaker_comprehensionChance(EntityLivingBase instance, Operation<Boolean> original) {
        return original.call(instance) && instance.getRNG().nextFloat() < ConfigHandler.enchanterTools.comprehensionChance;
    }
}
