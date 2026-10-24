package nischitweaker.mixins.grapplinghook;

import com.yyon.grapplinghook.CommonProxyClass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CommonProxyClass.class)
public abstract class CommonProxyClassMixin {

    //ObfuscationReflectionHelper doesn't remap SRG to MCP names in dev, so finding func_184342_d crashes
    @ModifyArg(
            method = "preInit",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/ObfuscationReflectionHelper;findMethod(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/reflect/Method;"),
            index = 1,
            remap = false
    )
    private String nischitweaker_fixDevCapturePosition(String srgName) {
        return "captureCurrentPosition";
    }
}
