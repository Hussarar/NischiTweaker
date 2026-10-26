package nischitweaker.jei.qualitytools;

import com.tmtravlr.qualitytools.QualityToolsMod;
import com.tmtravlr.qualitytools.reforging.BlockReforgingStation;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nischitweaker.Tags;

import javax.annotation.Nonnull;

public class QTRecipeCategory implements IRecipeCategory<IRecipeWrapper> {
    public static final String UID = "qualitytools.reforgingstation";
    public static final int WIDTH = 160;
    private static final int SLOTS_X = (WIDTH - 18) / 2;
    private static final int SLOTS_HEIGHT = 58;
    public static final int LABELS_TOP = SLOTS_HEIGHT + 4;

    private final IDrawable icon;
    private final IDrawable slots;
    //Set after the recipes are created, so that the tallest list of quality labels fits
    private int labelsHeight = 0;

    public QTRecipeCategory(IGuiHelper guiHelper) {
        this.slots = guiHelper.createDrawable(new ResourceLocation(Tags.MODID, "textures/gui/qt_reforging_station_jei.png"), 0, 0, 18, SLOTS_HEIGHT);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockReforgingStation.INSTANCE));
    }

    public void setLabelsHeight(int labelsHeight) {
        this.labelsHeight = labelsHeight;
    }

    private final IDrawable background = new IDrawable() {
        @Override
        public int getWidth() {
            return WIDTH;
        }

        @Override
        public int getHeight() {
            return labelsHeight > 0 ? LABELS_TOP + labelsHeight : SLOTS_HEIGHT;
        }

        @Override
        public void draw(@Nonnull Minecraft minecraft, int xOffset, int yOffset) {
            slots.draw(minecraft, xOffset + SLOTS_X, yOffset);
        }
    };

    @Override
    @Nonnull
    public String getUid() {
        return UID;
    }

    @Override
    @Nonnull
    public String getTitle() {
        return BlockReforgingStation.INSTANCE.getLocalizedName();
    }

    @Override
    @Nonnull
    public String getModName() {
        return QualityToolsMod.MOD_NAME;
    }

    @Override
    @Nonnull
    public IDrawable getBackground() {
        return background;
    }

    @Override
    @Nonnull
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(0, false, SLOTS_X, 0);
        guiItemStacks.init(1, true, SLOTS_X, 40);
        guiItemStacks.set(ingredients);
    }
}