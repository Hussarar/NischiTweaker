package nischitweaker.mixins.locks;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TileEntityLockableLoot.class)
public interface TileEntityLockableLootInvoker {

    //Locks' own invoker is named getItems which collides with the MCP name in dev, so it resolves to the protected method
    @Invoker("getItems")
    NonNullList<ItemStack> nischitweaker_getItems();
}
