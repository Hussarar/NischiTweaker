package nischitweaker.mixins.vanilla;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.WorldInfo;
import nischitweaker.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldInfo.class)
public abstract class WorldInfoMixin_DefaultDifficulty {

    /**
     * This constructor is only used when creating a new world
     */
    @ModifyExpressionValue(
            method = "<init>(Lnet/minecraft/world/WorldSettings;Ljava/lang/String;)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/world/storage/WorldInfo;DEFAULT_DIFFICULTY:Lnet/minecraft/world/EnumDifficulty;")
    )
    private EnumDifficulty nischitweaker_newWorldDifficulty(EnumDifficulty original) {
        return EnumDifficulty.valueOf(ConfigHandler.vanilla.newWorldDifficulty.name());
    }
}
