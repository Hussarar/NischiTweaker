package nischitweaker.mixins.reskillable;

import codersafterdark.reskillable.skill.farming.TraitMoreWheat;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TraitMoreWheat.class)
public abstract class TraitMoreWheatMixin {

    @WrapOperation(
            method = "onBlockDrops",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;")
    )
    private Block nischitweaker_onlyFullyGrownWheat(IBlockState state, Operation<Block> original) {
        Block block = original.call(state);
        if (!(block instanceof BlockCrops)) return block;

        // not fully grown wheat shouldn't drop extra wheat
        return !((BlockCrops) block).isMaxAge(state) ? Blocks.AIR : block;
    }
}
