package nischitweaker.mixins.globalgamerules;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import se.gory_moon.globalgamerules.WorldEvents;
import se.gory_moon.globalgamerules.config.GGRConfig;

import java.util.HashMap;
import java.util.function.BiConsumer;

@Mixin(WorldEvents.class)
public abstract class WorldEventsMixin {

    /**
     * ordinal 0 is the config gamerules loop, ordinal 1 the custom gamerules
     */
    @WrapOperation(
            method = "onWorldLoad",
            at = @At(value = "INVOKE", target = "Ljava/util/HashMap;forEach(Ljava/util/function/BiConsumer;)V", ordinal = 0),
            remap = false
    )
    private static void nischitweaker_onlyApplySpecificRules(HashMap<String, GGRConfig.Value> rules, BiConsumer<String, GGRConfig.Value> action, Operation<Void> original) {
        original.call(rules, (BiConsumer<String, GGRConfig.Value>) (rule, value) -> {
            if (ConfigHandler.globalgamerules.appliedGameRules.contains(rule))
                action.accept(rule, value);
        });
    }

    @ModifyExpressionValue(
            method = "onWorldLoad",
            at = @At(value = "INVOKE", target = "Lse/gory_moon/globalgamerules/config/GGRConfig$Value;getIntegerValue()I"),
            remap = false
    )
    private static int nischitweaker_onlyApplySpecificDifficulty(int original) {
        return ConfigHandler.globalgamerules.appliedGameRules.contains("worldDifficulty") ? original : -1;
    }

    @ModifyExpressionValue(
            method = "onWorldLoad",
            at = @At(value = "INVOKE", target = "Lse/gory_moon/globalgamerules/config/GGRConfig$Value;getBooleanValue()Z"),
            remap = false
    )
    private static boolean nischitweaker_onlyApplySpecificDifficultyLock(boolean original) {
        return original && ConfigHandler.globalgamerules.appliedGameRules.contains("worldDifficultyLocked");
    }
}
