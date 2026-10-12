package nischitweaker.asm.enchantertools;

import org.objectweb.asm.ClassWriter;
import meldexun.asmutil2.HashMapClassNodeClassTransformer;
import meldexun.asmutil2.IClassTransformerRegistry;
import net.minecraft.launchwrapper.IClassTransformer;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.util.Annotations;

public class EnchanterToolsClassTransformer extends HashMapClassNodeClassTransformer implements IClassTransformer {

    @Override
    protected void registerTransformers(IClassTransformerRegistry registry) {
        registry.add("com.sirsquidly.enchanter_tools.mixin.MixinEnchantmentHelper", ClassWriter.COMPUTE_FRAMES, classNode -> {
            classNode.methods.removeIf(method ->
                    method.name.equals("enchantertools$preventNegativePower") &&
                    Annotations.getVisible(method, Inject.class) != null // will be replaced by ModifyArg in next version probably
            );
        });
    }
}
