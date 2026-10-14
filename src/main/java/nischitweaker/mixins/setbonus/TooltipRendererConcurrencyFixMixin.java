package nischitweaker.mixins.setbonus;

import com.fantasticsource.setbonus.client.TooltipRenderer;
import com.fantasticsource.setbonus.common.Bonus;
import com.fantasticsource.setbonus.common.bonusrequirements.setrequirement.Set;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.LinkedHashSet;

@Mixin(TooltipRenderer.class)
public abstract class TooltipRendererConcurrencyFixMixin {
    @ModifyExpressionValue(
            method = "tooltips",
            at = @At(value = "FIELD", target = "Lcom/fantasticsource/setbonus/SetBonusData;sets:Ljava/util/LinkedHashSet;"),
            remap = false
    )
    private static LinkedHashSet<Set> nischitweaker_copySets(LinkedHashSet<Set> original) {
        return new LinkedHashSet<>(original);
    }

    @ModifyExpressionValue(
            method = "tooltips",
            at = @At(value = "FIELD", target = "Lcom/fantasticsource/setbonus/SetBonusData;bonuses:Ljava/util/LinkedHashSet;"),
            remap = false
    )
    private static LinkedHashSet<Bonus> nischitweaker_copyBonuses(LinkedHashSet<Bonus> original) {
        return new LinkedHashSet<>(original);
    }
}
