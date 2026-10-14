package nischitweaker.asm.enchantertools;

import meldexun.asmutil2.HashMapClassNodeClassTransformer;
import meldexun.asmutil2.IClassTransformerRegistry;
import net.minecraft.launchwrapper.IClassTransformer;
import nischitweaker.CompatUtil;
import nischitweaker.config.ConfigHandler;
import org.objectweb.asm.ClassWriter;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.util.Annotations;

public class EnchanterToolsClassTransformer extends HashMapClassNodeClassTransformer implements IClassTransformer {

    @Override
    protected void registerTransformers(IClassTransformerRegistry registry) {
        if(ConfigHandler.enchanterTools.fixMinEnchantLvl && CompatUtil.isInRange("enchanter_tools", "[1.1.1a]"))
        registry.add("com.sirsquidly.enchanter_tools.mixin.MixinEnchantmentHelper", ClassWriter.COMPUTE_FRAMES, classNode -> {
            classNode.methods.removeIf(method ->
                    method.name.equals("enchantertools$preventNegativePower") &&
                    Annotations.getVisible(method, Inject.class) != null // will be replaced by ModifyArg in next version probably
            );
        });
    }
}
