package nischitweaker.mixins.invtweaks;

import invtweaks.container.ContainerSectionManager;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "invtweaks.InvTweaksHandlerAutoRefill$1")
public abstract class InvTweaksAutoRefillRaceConditionFixMixin {

    @Shadow(remap = false) private ContainerSectionManager containerMgr;
    @Shadow(remap = false) private int targetedSlot;
    @Shadow(remap = false) private String expectedItemId;
    @Shadow(remap = false) private boolean refillBeforeBreak;

    /**
     * Fix race condition in InventoryTweaks autorefill with crates/shulkers in multiplayer
     * When placing (especially unstackable) items rapidly, autorefill executes before server confirms placement
     * This causes inventory desync and item drops
     */
    @Inject(
            method = "run",
            at = @At(value = "INVOKE", target = "Linvtweaks/container/ContainerSectionManager;move(II)Z", ordinal = 0),
            cancellable = true,
            remap = false
    )
    private void nischitweaker_preventAutorefillRaceCondition(CallbackInfo ci) {
        ItemStack targetStack = containerMgr.getItemStack(targetedSlot);

        if (!targetStack.isEmpty() && expectedItemId != null) {
            String targetItemId = targetStack.getItem().getRegistryName().toString();

            // If target slot already has the same item we're trying to refill
            if (targetItemId.equals(expectedItemId)) {
                // For refillBeforeBreak: check if target is actually below durability threshold
                if (refillBeforeBreak && targetStack.isItemDamaged())
                    return; // Legitimate refillBeforeBreak - allow the swap

                // Race condition: server already refilled or hasn't confirmed placement - abort
                ci.cancel();
            }
        }
    }
}
