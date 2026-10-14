package nischitweaker.config.folders;

import net.minecraftforge.common.config.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZenUtilsConfig {

    @Config.Comment("Makes #mixin Share work in ZenUtils 1.27.5. Was fixed in ZenUtils 1.28.7, this backports the fix.")
    @Config.Name("Fix Share Annotation (ASM Toggle)")
    public boolean fixShareAnnotation = true;

    @Config.Comment("List of classes to replace ConfigAnytime.register with zenutils ConfigAnytimeAnytime.register")
    @Config.Name("ConfigAnytime Dependency Replacements (ASM Toggle)")
    public List<String> configAnytimeClassPatches = new ArrayList<>(Arrays.asList(
            "youyihj.zenutils.impl.core.Configuration",
            "git.jbredwards.baubleye.PatchConfigs"
    ));
}
