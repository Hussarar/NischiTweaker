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
        LocksItems.add("dragonbone_lock_pick", DragonboneLockPick.DRAGONBONE_LOCK_PICK);
    }

    @Inject(method = "register", at = @At("TAIL"), remap = false)
    private static void nischitweaker_addDragonboneLockpickToOreDict(CallbackInfo ci) {
        OreDictionary.registerOre("locksLockPick", DragonboneLockPick.DRAGONBONE_LOCK_PICK);
    }
}
