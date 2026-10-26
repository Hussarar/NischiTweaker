package nischitweaker.mixins.locks;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import melonslise.locks.common.init.LocksItems;
import melonslise.locks.common.item.LockPickItem;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import nischitweaker.config.ConfigHandler;
import nischitweaker.features.DragonboneLock;
import nischitweaker.features.AdditionalLockPicks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocksItems.class)
public abstract class LocksItemsMixin {

    //modify existing lockpick strengths
    @WrapOperation(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lmelonslise/locks/common/init/LocksItems;add(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;"),
            remap = false
    )
    private static Item nischitweaker_modifyLockpickStrengths(String name, Item item, Operation<Item> original){
        if(!(item instanceof LockPickItem)) return original.call(name, item); //only modify lockpicks
        if(!ConfigHandler.locks.lockPickStrengths.containsKey(name)) return original.call(name, item); //only modify the lockpicks in our config

        ((LockPickItemAccessor) item).setStrength(ConfigHandler.locks.lockPickStrengths.get(name)); //modify strength
        AdditionalLockPicks.LOCKPICKS.put(name, (LockPickItem) item); // keep track of existing lockpicks so we don't register them twice
        return original.call(name, item); //LocksItems.add(name, item)
    }

    @Inject(method = "<clinit>", at = @At("TAIL"), remap = false)
    private static void nischitweaker_registerDragonboneLockpick(CallbackInfo ci) {
        AdditionalLockPicks.registerLockPicks();
        DragonboneLock.registerLocks();
    }

    @Inject(method = "register", at = @At("TAIL"), remap = false)
    private static void nischitweaker_addDragonboneLockpickToOreDict(CallbackInfo ci) {
        AdditionalLockPicks.LOCKPICKS.values().forEach(item ->
                OreDictionary.registerOre("locksLockPick", item)
        );
    }
}
