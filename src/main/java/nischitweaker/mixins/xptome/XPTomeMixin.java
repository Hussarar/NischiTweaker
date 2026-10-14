package nischitweaker.mixins.xptome;

import bl4ckscor3.mod.xptome.ItemXPTome;
import bl4ckscor3.mod.xptome.XPTome;
import net.minecraft.item.Item;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.RegistryEvent;
import nischitweaker.features.GreaterXPTome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(XPTome.class)
public abstract class XPTomeMixin {

    @Inject(method = "onRegisterItems", at = @At("TAIL"), remap = false)
    private static void nischitweaker_addGreaterTome(RegistryEvent.Register<Item> event, CallbackInfo ci) {
        event.getRegistry().register(GreaterXPTome.GREATER_XP_TOME);
    }

    @Inject(method = "onAnvilUpdate", at = @At("TAIL"), remap = false)
    private static void nischitweaker_cancelTomeRepair(AnvilUpdateEvent event, CallbackInfo ci) {
        if (event.getLeft().getItem() instanceof ItemXPTome || event.getRight().getItem() instanceof ItemXPTome)
            event.setCanceled(true);
    }
}
