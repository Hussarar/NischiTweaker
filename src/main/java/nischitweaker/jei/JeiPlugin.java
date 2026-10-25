package nischitweaker.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraftforge.fml.common.Loader;
import nischitweaker.config.ConfigHandler;
import nischitweaker.jei.qualitytools.QTJeiPlugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@JEIPlugin
public class JeiPlugin implements IModPlugin {

    private final List<IModPlugin> plugins = new ArrayList<>();

    public JeiPlugin() {
        //Compat plugins only get instantiated if their mod is loaded, so their classes don't get loaded otherwise
        if (ConfigHandler.qualitytools.jeiReforgingCategory && Loader.isModLoaded("qualitytools")) plugins.add(new QTJeiPlugin());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        plugins.forEach(p -> p.registerCategories(registry));
    }

    @Override
    public void register(IModRegistry registry) {
        plugins.forEach(p -> p.register(registry));
    }
}
