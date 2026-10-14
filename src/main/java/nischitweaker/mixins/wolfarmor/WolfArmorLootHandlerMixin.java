package nischitweaker.mixins.wolfarmor;

import dev.satyrn.wolfarmor.common.loot.LootHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LootHandler.class)
public abstract class WolfArmorLootHandlerMixin {

    @ModifyArg(
            method = "getInjectPool",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/storage/loot/RandomValueRange;<init>(F)V")
    )
    private float nischitweaker_noBonusWolfArmor(float original) {
        return 0.0F; // replace 1-1 bonus rolls with 0-0 bonus rolls
    }
}
