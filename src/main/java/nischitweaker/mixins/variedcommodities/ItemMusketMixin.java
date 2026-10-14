package nischitweaker.mixins.variedcommodities;

import nischitweaker.config.ConfigHandler;
import noppes.vc.items.ItemMusket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemMusket.class)
public abstract class ItemMusketMixin {

    @ModifyConstant(method = "onUsingTick", constant = @Constant(intValue = 60), remap = false)
    private int nischitweaker_changeMusketLoadingTime(int original) {
        return ConfigHandler.variedcommodities.musketLoadingTime; // loading time in ticks, 20 ticks = 1 sec
    }
}
