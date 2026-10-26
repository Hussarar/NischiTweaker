package nischitweaker.jei.qualitytools;

import com.tmtravlr.qualitytools.config.CustomMaterial;
import com.tmtravlr.qualitytools.config.QualityType;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import nischitweaker.config.ConfigHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QTRecipeWrapper implements IRecipeWrapper {
    public final List<ItemStack> gear = new ArrayList<>();
    public final List<ItemStack> materials;
    public final QTQualityLabels qualityLabels;
    private static final Map<CustomMaterial, List<ItemStack>> customMaterialToStacks = new HashMap<>();

    public QTRecipeWrapper(ItemStack gear, List<ItemStack> materials, QualityType qualityType){
        this.gear.add(gear);
        this.materials = materials;
        this.qualityLabels = ConfigHandler.qualitytools.jeiShowQualities ? QTQualityLabels.get(qualityType) : null;
    }

    public void addMaterial(CustomMaterial newMaterial) {
        this.materials.addAll(getStacksForCustomMaterial(newMaterial));
    }

    public static List<ItemStack> getStacksForCustomMaterial(CustomMaterial mat){
        if(customMaterialToStacks.containsKey(mat))
            return customMaterialToStacks.get(mat);
        else {
            List<ItemStack> toReturn;

            if (mat.item != null) {
                ItemStack newStack = new ItemStack(mat.item, 1, mat.meta == 32767 ? 0 : mat.meta);
                if (mat.tag != null)
                    newStack.setTagCompound(mat.tag);
                toReturn = Collections.singletonList(newStack);
            } else if (mat.oreDict != null)
                toReturn = OreDictionary.getOres(mat.oreDict);
            else
                toReturn = Collections.emptyList();

            customMaterialToStacks.put(mat, toReturn);
            return toReturn;
        }
    }

    public static void clearCache(){
        customMaterialToStacks.clear();
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, Collections.singletonList(materials));
        ingredients.setOutputLists(VanillaTypes.ITEM, Collections.singletonList(gear));
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        if (qualityLabels != null) qualityLabels.draw(minecraft, QTRecipeCategory.LABELS_TOP);
    }

    @Override
    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return qualityLabels != null ? qualityLabels.getTooltip(QTRecipeCategory.LABELS_TOP, mouseX, mouseY) : Collections.emptyList();
    }
}
