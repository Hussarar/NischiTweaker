package nischitweaker.asm.invtweaks;

import net.minecraftforge.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

/**
 * Inventory Tweaks registers its AT as a coremod AT class, which ForgeGradle doesn't remap to MCP names indev
 */
public class InvTweaksDevAccessTransformer extends AccessTransformer {

    public InvTweaksDevAccessTransformer() throws IOException {
        super("nischitweaker_invtweaks_dev_at.cfg");
    }
}
