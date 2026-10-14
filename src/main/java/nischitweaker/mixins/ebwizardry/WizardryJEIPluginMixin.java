package nischitweaker.mixins.ebwizardry;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import electroblob.wizardry.integration.jei.WizardryJEIPlugin;
import mezz.jei.api.IModRegistry;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WizardryJEIPlugin.class)
public abstract class WizardryJEIPluginMixin {

    @WrapWithCondition(
            method = "register",
            at = @At(value = "INVOKE", target = "Lelectroblob/wizardry/integration/jei/WizardryJEIPlugin;addEnchantmentInfo(Lmezz/jei/api/IModRegistry;Lnet/minecraft/enchantment/Enchantment;)V"),
            remap = false
    )
    private boolean nischitweaker_dontRegisterEnchantmentInfos(IModRegistry registry, Enchantment enchantment) {
        return false; // Don't call original - skip registration
    }
}
