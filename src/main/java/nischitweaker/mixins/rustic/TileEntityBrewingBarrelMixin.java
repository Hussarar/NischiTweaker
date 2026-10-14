package nischitweaker.mixins.rustic;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rustic.common.blocks.BlockBrewingBarrel;
import rustic.common.inventory.ExternalItemHandler;
import rustic.common.tileentity.TileEntityBrewingBarrel;

@Mixin(TileEntityBrewingBarrel.class)
public abstract class TileEntityBrewingBarrelMixin extends TileEntity {
    @Shadow(remap = false) private ExternalItemHandler externalStackHandler;

    @Unique private IItemHandler nischitweaker$handlerJuiceIn;
    @Unique private IItemHandler nischitweaker$handlerNewWineIn;
    @Unique private IItemHandler nischitweaker$handlerOldWineIn;
    @Unique private IItemHandler nischitweaker$handlerOut;

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void nischitweaker_initHopperHandlers(CallbackInfo ci) {
        // in slotIDs:
        final int SLOT_JUICE_IN = 0;
        final int SLOT_WINE_IN = 1;
        final int SLOT_OLD_WINE_IN = 2;

        // out slotIDs:
        final int SLOT_JUICE_OUT = 3;
        final int SLOT_OLD_WINE_OUT = 5;

        // upper hopper puts new juice in (middle slot)
        nischitweaker$handlerJuiceIn = new RangedWrapper(externalStackHandler, SLOT_JUICE_IN, SLOT_JUICE_IN + 1);
        // back hopper puts empty bottles to fill with new wine in (right slot)
        nischitweaker$handlerNewWineIn = new RangedWrapper(externalStackHandler, SLOT_WINE_IN, SLOT_WINE_IN + 1);
        // side hopper puts old wine in (left slot)
        nischitweaker$handlerOldWineIn = new RangedWrapper(externalStackHandler, SLOT_OLD_WINE_IN, SLOT_OLD_WINE_IN + 1);

        // out = down hopper takes all bottom slots
        nischitweaker$handlerOut = new RangedWrapper(externalStackHandler, SLOT_JUICE_OUT, SLOT_OLD_WINE_OUT + 1);
    }

    @ModifyReturnValue(method = "getCapability", at = @At("RETURN"), remap = false)
    @SuppressWarnings("unchecked")
    private <T> T nischitweaker_getHopperCapability(T original, Capability<T> capability, EnumFacing facing) {
        if (facing == null) return original;
        if (capability != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return original;

        if (facing == EnumFacing.DOWN) return (T) nischitweaker$handlerOut;
        if (facing == EnumFacing.UP) return (T) nischitweaker$handlerJuiceIn;

        EnumFacing.Axis blockFacingAxis = this.world.getBlockState(this.pos).getValue(BlockBrewingBarrel.FACING).getAxis();
        if (facing.getAxis() == blockFacingAxis) return (T) nischitweaker$handlerNewWineIn;
        else return (T) nischitweaker$handlerOldWineIn;
    }
}
