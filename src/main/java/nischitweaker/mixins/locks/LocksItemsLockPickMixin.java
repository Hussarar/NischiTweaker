package nischitweaker.mixins.locks;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import melonslise.locks.common.init.LocksItems;
import melonslise.locks.common.item.LockPickItem;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import nischitweaker.config.ConfigHandler;
import nischitweaker.features.AdditionalLocksItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocksItems.class)
public abstract class LocksItemsLockPickMixin {

    //modify existing lockpick strengths
    @WrapOperation(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lmelonslise/locks/common/init/LocksItems;add(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;"),
            remap = false
    )
    private static Item nischitweaker_modifyLockPicks(String name, Item item, Operation<Item> original){
        if(item instanceof LockPickItem && ConfigHandler.locks.lockPickStrengths.containsKey(name)) { //modify the lockpicks contained in our config
            ((LockPickItemAccessor) item).setStrength(ConfigHandler.locks.lockPickStrengths.get(name)); //modify strength
            AdditionalLocksItems.LOCKPICKS.put(name, (LockPickItem) item); // keep track of existing lockpicks so we don't register them twice
        }
        return original.call(name, item); //LocksItems.add(name, item)
    }

    @Inject(method = "<clinit>", at = @At("TAIL"), remap = false)
    private static void nischitweaker_registerAdditionalLockPicks(CallbackInfo ci) {
        AdditionalLocksItems.registerLockPicks();
    }

    @Inject(method = "register", at = @At("TAIL"), remap = false)
    private static void nischitweaker_addLockPicksToOreDict(CallbackInfo ci) {
        AdditionalLocksItems.LOCKPICKS.values().forEach(item ->
                OreDictionary.registerOre("locksLockPick", item)
        );
    }
}
