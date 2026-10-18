package nischitweaker.recipes;

import bl4ckscor3.mod.xptome.ItemXPTome;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import nischitweaker.features.GreaterXPTome;

import javax.annotation.Nonnull;

public class RecipeGreaterXPTomeUpgrade extends ShapedOreRecipe {

    public RecipeGreaterXPTomeUpgrade(IRecipe original, CraftingHelper.ShapedPrimer primer) {
        // copy json provided recipe inputs+outputs
        super(new ResourceLocation(original.getGroup()), original.getRecipeOutput(), primer);
        setRegistryName(original.getGroup());
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        ItemStack output = super.getCraftingResult(inv);

        ItemStack tomeStack = findXPTome(inv);
        if (!tomeStack.isEmpty()) {
            int storedXP = GreaterXPTome.getStoredXP(tomeStack);
            output.setTagInfo(GreaterXPTome.STORED_KEY, new NBTTagInt(storedXP));
        }

        return output;
    }

    private ItemStack findXPTome(InventoryCrafting inv) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.getItem() instanceof ItemXPTome) return stack;
        }
        return ItemStack.EMPTY;
    }
}
