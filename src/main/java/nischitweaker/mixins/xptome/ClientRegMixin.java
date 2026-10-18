package nischitweaker.mixins.xptome;

import bl4ckscor3.mod.xptome.ClientReg;
import bl4ckscor3.mod.xptome.XPTome;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import nischitweaker.config.ConfigHandler;
import nischitweaker.features.GreaterXPTome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientReg.class)
public abstract class ClientRegMixin {

    @Inject(method = "onModelRegistry", at = @At("TAIL"), remap = false)
    private static void nischitweaker_addGreaterTomeModel(CallbackInfo ci) {
        ConfigHandler.xptome.additionalTomes.keySet().forEach(name ->
                ModelLoader.setCustomModelResourceLocation(
                        GreaterXPTome.XPTomes.get(name),
                        0,
                        new ModelResourceLocation(XPTome.MODID + ":" + name, "inventory")
                )
        );
    }
}
