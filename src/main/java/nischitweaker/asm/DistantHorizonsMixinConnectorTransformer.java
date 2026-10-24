package nischitweaker.asm;

import meldexun.asmutil2.ASMUtil;
import meldexun.asmutil2.HashMapClassNodeClassTransformer;
import meldexun.asmutil2.IClassTransformerRegistry;
import net.minecraft.launchwrapper.IClassTransformer;
import nischitweaker.config.ConfigHandler;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodInsnNode;

public class DistantHorizonsMixinConnectorTransformer extends HashMapClassNodeClassTransformer implements IClassTransformer {

    @Override
    protected void registerTransformers(IClassTransformerRegistry registry) {
        if(!ConfigHandler.distantHorizons.removeMixinBooterDependency) return;

        // Replace both ModDiscoverer.isModPresent(modid) calls by
        // Hook.returnTrue(modid)
        registry.add("com.seibel.distanthorizons.forge112.DistantHorizonsMixinConnector", "connect", ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, method -> {
            MethodInsnNode isModPresent = ASMUtil.first(method).methodInsn("isModPresent").find();
            ASMUtil.replace(method, isModPresent,
                    new MethodInsnNode(Opcodes.INVOKESTATIC, "nischitweaker/asm/DistantHorizonsMixinConnectorTransformer$Hook", "returnTrue", "(Ljava/lang/String;)Z", false)
            );
            MethodInsnNode isModPresent2 = ASMUtil.nextExclusive(method, isModPresent).methodInsn("isModPresent").find();
            ASMUtil.replace(method, isModPresent2,
                    new MethodInsnNode(Opcodes.INVOKESTATIC, "nischitweaker/asm/DistantHorizonsMixinConnectorTransformer$Hook", "returnTrue", "(Ljava/lang/String;)Z", false)
            );
        });
    }

    public static class Hook {
        public static boolean returnTrue(String modid) {
            return true;
        }
    }
}

