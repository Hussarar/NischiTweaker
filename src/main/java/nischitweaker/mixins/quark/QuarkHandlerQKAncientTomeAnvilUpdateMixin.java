package nischitweaker.mixins.quark;

import com.charles445.rltweaker.handler.QuarkHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import svenhjol.charm.enchanting.feature.CurseBreak;

import java.util.Map;

@Mixin(QuarkHandler.QKAncientTomeAnvilUpdate.class)
public abstract class QuarkHandlerQKAncientTomeAnvilUpdateMixin {

    @ModifyVariable(
            method = "onUseItem",
            at = @At(value = "LOAD", ordinal = 0),
            name = "matched",
            remap = false
    )
    private boolean nischitweaker_applyCurseBreak(boolean matched, @Local(name = "tomeEnchants") Map<Enchantment, Integer> tomeEnchants, @Local(name = "itemEnchants") Map<Enchantment, Integer> itemEnchants) {
        if (matched) return true;
        if (tomeEnchants.size() != 1) return matched; // usually size 1 but just to be sure
        if (tomeEnchants.keySet().stream().noneMatch(ench -> ench == CurseBreak.enchantment))
            return matched;

        itemEnchants.keySet().removeIf(ench ->
                ench != null && ench.isCurse()
        );

        return true;
    }
}
