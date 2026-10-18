package nischitweaker.mixins.locks;

import melonslise.locks.common.init.LocksItems;
import net.minecraftforge.oredict.OreDictionary;
import nischitweaker.features.DragonboneLockPick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocksItems.class)
public abstract class LocksItemsMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"), remap = false)
    private static void nischitweaker_registerDragonboneLockpick(CallbackInfo ci) {
        DragonboneLockPick.registerLockPicks();
    }

    @Inject(method = "register", at = @At("TAIL"), remap = false)
    private static void nischitweaker_addDragonboneLockpickToOreDict(CallbackInfo ci) {
        DragonboneLockPick.LOCKPICKS.values().forEach(item ->
                OreDictionary.registerOre("locksLockPick", item)
        );
    }
}
