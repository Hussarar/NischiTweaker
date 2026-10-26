package nischitweaker.jei.qualitytools;

import materialtweaker.handlers.CustomConfigHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

public class MaterialTweakerCompat {

    //MaterialTweaker injects into getIsRepairable of Item, ItemSword, ItemTool, ItemArmor, ItemShield and ItemElytra and replaces the repair item for configured items
    @Nullable
    public static ItemStack getOverrideRepairStack(Item gearItem) {
        if (gearItem.getRegistryName() == null) return null;
        String[] entry = CustomConfigHandler.getItemOverrideRepairs(gearItem.getRegistryName().toString());
        if (entry == null) return null;

        Item repairItem = Item.getByNameOrId(entry[0]);
        if (repairItem == null) return null;
        return new ItemStack(repairItem, 1, entry[1].equals("*") ? OreDictionary.WILDCARD_VALUE : Integer.parseInt(entry[1]));
    }
}
