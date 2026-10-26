package nischitweaker.jei.qualitytools;

import luckified.ModConfig;

import java.util.Arrays;

//Only gets called if Luckified is loaded
public class LuckifiedCompat {

    //Luckified increases the reforging weight of qualities in its rare list by the player's luck
    public static boolean isLuckAffected(String qualityName) {
        return ModConfig.qualityTools.rareQualityWeightPerLuck > 0 && Arrays.asList(ModConfig.qualityTools.rareQualityList).contains(qualityName);
    }
}
