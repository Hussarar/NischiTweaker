package nischitweaker.asm.bloodmoon;

import meldexun.asmutil2.HashMapClassNodeClassTransformer;
import meldexun.asmutil2.IClassTransformerRegistry;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class BloodmoonDevMappingsTransformer extends HashMapClassNodeClassTransformer implements IClassTransformer {

    @Override
    protected void registerTransformers(IClassTransformerRegistry registry) {
        //Bloodmoon reads SRG->MCP mappings from ForgeGradle 2's GradleStartCommon.CSV_DIR indev, read them from ForgeGradle 3's srg-mcp file instead
        registry.add("lumien.bloodmoon.asm.MCPNames", "<clinit>", ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, method -> {
            InsnList insns = new InsnList();
            insns.add(new InsnNode(Opcodes.ICONST_0));
            insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "nischitweaker/asm/bloodmoon/BloodmoonDevMappingsTransformer$Hook", "readMappings", "(Z)Ljava/util/Map;", false));
            insns.add(new FieldInsnNode(Opcodes.PUTSTATIC, "lumien/bloodmoon/asm/MCPNames", "fields", "Ljava/util/Map;"));
            insns.add(new InsnNode(Opcodes.ICONST_1));
            insns.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "nischitweaker/asm/bloodmoon/BloodmoonDevMappingsTransformer$Hook", "readMappings", "(Z)Ljava/util/Map;", false));
            insns.add(new FieldInsnNode(Opcodes.PUTSTATIC, "lumien/bloodmoon/asm/MCPNames", "methods", "Ljava/util/Map;"));
            insns.add(new InsnNode(Opcodes.RETURN));

            method.instructions.clear();
            method.instructions.add(insns);
            method.tryCatchBlocks.clear();
            if(method.localVariables != null) method.localVariables.clear();
        });
    }

    public static class Hook {
        public static Map<String, String> readMappings(boolean methods) {
            String srgMcp = System.getProperty("net.minecraftforge.gradle.GradleStart.srg.srg-mcp");
            if(srgMcp == null) throw new RuntimeException("Couldn't find SRG->MCP mappings");

            Map<String, String> mappings = new HashMap<>();
            try {
                for(String line : Files.readAllLines(new File(srgMcp).toPath(), StandardCharsets.UTF_8)) {
                    //FD: owner/srg owner/mcp
                    //MD: owner/srg desc owner/mcp desc
                    if(!line.startsWith(methods ? "MD: " : "FD: ")) continue;
                    String[] parts = line.split(" ");
                    String srg = parts[1].substring(parts[1].lastIndexOf('/') + 1);
                    String mcpFull = methods ? parts[3] : parts[2];
                    mappings.put(srg, mcpFull.substring(mcpFull.lastIndexOf('/') + 1));
                }
            } catch(IOException e) {
                throw new RuntimeException("Couldn't read SRG->MCP mappings", e);
            }
            return mappings;
        }
    }
}
