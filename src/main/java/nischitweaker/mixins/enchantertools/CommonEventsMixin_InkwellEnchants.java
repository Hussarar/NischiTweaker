package nischitweaker.mixins.enchantertools;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.sirsquidly.enchanter_tools.common.CommonEvents;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

@Mixin(CommonEvents.class)
public abstract class CommonEventsMixin_InkwellEnchants {

    @WrapWithCondition(
            method = "createEnchantedInkwell",
            at = @At(value = "INVOKE", target = "Lcom/sirsquidly/enchanter_tools/common/CommonEvents;applyRandomSingleMaxEnchant(Ljava/util/Random;Lnet/minecraft/item/ItemStack;IIZ)V"),
            remap = false
    )
    private static boolean nischitweaker_disableInkwellEnchant(Random rand, ItemStack stack, int minPower, int maxPower, boolean maxLevel) {
        return false; // don't enchant inkwells in lootpools
    }
}
