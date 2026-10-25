package nischitweaker.mixins.locks;

import melonslise.locks.common.item.LockPickItem;
import net.minecraft.util.ResourceLocation;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LockPickItem.class)
public abstract class LockPickItemMixin_Tooltip {

    //Locks reads the strength itself for the tooltip instead of using getStrength
    @ModifyVariable(method = "addInformation", at = @At(value = "STORE"))
    private float nischitweaker_overrideTooltipStrength(float original) {
        LockPickItem item = (LockPickItem) (Object) this;
        if (original != item.strength) return original; //stack has its own strength nbt

        ResourceLocation id = item.getRegistryName();
        if (id == null || !"locks".equals(id.getNamespace())) return original;

        Float strength = ConfigHandler.locks.lockPickStrengths.get(id.getPath());
        return strength == null ? original : Math.max(0F, strength);
    }
}
