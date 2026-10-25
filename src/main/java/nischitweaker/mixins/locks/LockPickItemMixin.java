package nischitweaker.mixins.locks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import melonslise.locks.common.item.LockPickItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LockPickItem.class)
public abstract class LockPickItemMixin {

    //Used for both the pick breaking chance and the max lock complexity a pick can open
    @ModifyReturnValue(method = "getStrength", at = @At("RETURN"), remap = false)
    private static float nischitweaker_overrideStrength(float original, ItemStack stack) {
        //Stacks with their own strength nbt keep it
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey(LockPickItem.KEY_STRENGTH)) return original;

        ResourceLocation id = stack.getItem().getRegistryName();
        if (id == null || !"locks".equals(id.getNamespace())) return original;

        Float strength = ConfigHandler.locks.lockPickStrengths.get(id.getPath());
        return strength == null ? original : Math.max(0F, strength);
    }
}
