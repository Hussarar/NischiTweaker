package nischitweaker;

import fermiumbooter.FermiumRegistryAPI;
import fermiumbooter.util.FermiumJarScanner;
import fermiumbooter.util.ModInfo;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CompatUtil {
    private static Map<String, ModInfo> presentMods = null;

    @SuppressWarnings("deprecation")
    public static ModInfo getModInfo(String modId) {
        if (!FermiumJarScanner.isModPresent(modId)) return null; //mainly here to run the scanner
        if (presentMods == null){
            presentMods = ReflectionHelper.getPrivateValue(FermiumJarScanner.class, null, "presentMods");
            if(presentMods == null) presentMods = new HashMap<>();
        }
        return presentMods.get(modId);
    }

    public static boolean isInRange(String modId, String range) {
        ModInfo modInfo = getModInfo(modId);
        if(modInfo == null || modInfo.version == null) return false;
        try {
            VersionRange versionRange = VersionRange.createFromVersionSpec(range);
            return versionRange.containsVersion(modInfo.version);
        } catch (InvalidVersionSpecificationException e){
            return false;
        }
    }

    public static boolean hasName(String modId, String name) {
        ModInfo modInfo = getModInfo(modId);
        if(modInfo == null || modInfo.modName == null) return false;
        return modInfo.modName.equals(name);
    }

    public static void conditionallyAddMixin(String mixinJson, boolean late, int toggle, String... modids){
        FermiumRegistryAPI.enqueueMixin(late, mixinJson, () -> toggle >= 0 && Arrays.stream(modids).allMatch(FermiumJarScanner::isModPresent));
    }

    public static void conditionallyAddMixin(String mixinJson, boolean late, float toggle, String... modids){
        FermiumRegistryAPI.enqueueMixin(late, mixinJson, () -> toggle > 0 && Arrays.stream(modids).allMatch(FermiumJarScanner::isModPresent));
    }

    public static void conditionallyAddMixin(String mixinJson, boolean late, Collection<?> toggle, String... modids){
        FermiumRegistryAPI.enqueueMixin(late, mixinJson, () -> !toggle.isEmpty() && Arrays.stream(modids).allMatch(FermiumJarScanner::isModPresent));
    }

    public static void conditionallyAddMixin(String mixinJson, boolean late, Supplier<Boolean> toggle, String... modids){
        FermiumRegistryAPI.enqueueMixin(late, mixinJson, () -> toggle.get() && Arrays.stream(modids).allMatch(FermiumJarScanner::isModPresent));
    }
}
