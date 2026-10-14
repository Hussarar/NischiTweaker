package nischitweaker.mixins.grapplinghook;

import com.yyon.grapplinghook.GrappleConfig;
import com.yyon.grapplinghook.items.launcherItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(launcherItem.class)
public abstract class LauncherItemMixin {

    @Inject(method = "dorightclick", at = @At("HEAD"), remap = false)
    private void nischitweaker_addCooldown(ItemStack stack, World world, EntityPlayer player, CallbackInfo ci) {
        if (!world.isRemote) return;
        player.getCooldownTracker().setCooldown(stack.getItem(), GrappleConfig.getconf().ender_staff_recharge);
    }
}
