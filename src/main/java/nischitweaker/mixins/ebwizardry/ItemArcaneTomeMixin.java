package nischitweaker.mixins.ebwizardry;

import electroblob.wizardry.item.ItemArcaneTome;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemArcaneTome.class)
public abstract class ItemArcaneTomeMixin extends Item {

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void nischitweaker_modifyMaxStackSize(CallbackInfo ci) {
        this.setMaxStackSize(16);
    }
}
