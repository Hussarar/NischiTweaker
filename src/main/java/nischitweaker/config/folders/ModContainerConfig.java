package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModContainerConfig {

    @Config.Comment({
            "Map of modids to dependencies that should be removed.",
            "This will not magically remove actual dependencies, just the declaration in @Mod or mcmod.info"
    })
    @Config.Name("Removed declared Dependencies")
    public Map<String, ArrayList<String>> removedDependencies = new LinkedHashMap<>();
    private void initRemovedDependencies() {
        removedDependencies.put("zenutils", new ArrayList<>(Collections.singletonList("configanytime")));
        removedDependencies.put("distanthorizons", new ArrayList<>(Collections.singletonList("mixinbooter")));
    }

    public ModContainerConfig() {
        initRemovedDependencies();
    }
}
