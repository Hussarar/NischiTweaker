package nischitweaker.mixins.enchantertools;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.sirsquidly.enchanter_tools.init.EnchanterToolsItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

@Mixin(EntityVillager.ListEnchantedBookForEmeralds.class)
public class ListEnchantedBookForEmeraldsMixin {

    @ModifyExpressionValue(
            method = "addMerchantRecipe",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemEnchantedBook;getEnchantedItemStack(Lnet/minecraft/enchantment/EnchantmentData;)Lnet/minecraft/item/ItemStack;")
    )
    private ItemStack nischitweaker_replaceWithInkwell(
            ItemStack original,
            @Local(argsOnly = true) Random random,
            @Local Enchantment enchantment,
            @Local LocalIntRef originalLvl
    ) {
        // Check configured chance
        if (random.nextFloat() >= ConfigHandler.enchanterTools.inkwellReplaceChance) return original;

        ItemStack inkwellStack = new ItemStack(EnchanterToolsItems.ENCHANTED_INKWELL);
        // Add the rolled enchantment at max level
        int maxLvl = enchantment.getMaxLevel();
        inkwellStack.addEnchantment(enchantment, maxLvl);
        originalLvl.set(maxLvl); // so emerald price picks up on it

        return inkwellStack;
    }
}