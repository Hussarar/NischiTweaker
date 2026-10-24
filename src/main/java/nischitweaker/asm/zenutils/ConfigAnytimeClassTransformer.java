package nischitweaker.asm.zenutils;

import meldexun.asmutil2.ASMUtil;
import meldexun.asmutil2.HashMapClassNodeClassTransformer;
import meldexun.asmutil2.IClassTransformerRegistry;
import net.minecraft.launchwrapper.IClassTransformer;
import nischitweaker.config.ConfigHandler;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class ConfigAnytimeClassTransformer extends HashMapClassNodeClassTransformer implements IClassTransformer {

    @Override
    protected void registerTransformers(IClassTransformerRegistry registry) {
        // Replace ConfigAnytime.register(class) by zenutils internal
        // ConfigAnytimeAnytime.register(class, null)
        for (String className : ConfigHandler.zenUtils.configAnytimeClassPatches) {
            registry.add(className, "<clinit>", ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, method -> {
                MethodInsnNode register = ASMUtil.first(method).methodInsn("register").find();
                if (register != null) {
                    ASMUtil.replace(method, register, ASMUtil.listOf(
                            new InsnNode(Opcodes.ACONST_NULL),
                            new MethodInsnNode(Opcodes.INVOKESTATIC, "youyihj/zenutils/impl/config/ConfigAnytimeAnytime", "register", "(Ljava/lang/Class;Lnet/minecraftforge/fml/common/ModMetadata;)V", false)
                    ));
                }
            });
        }
    }
}