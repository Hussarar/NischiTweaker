package nischitweaker.mixins.locks;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import melonslise.locks.common.init.LocksItems;
import melonslise.locks.common.item.LockItem;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import nischitweaker.config.ConfigHandler;
import nischitweaker.config.folders.LocksConfig;
import nischitweaker.features.AdditionalLocksItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocksItems.class)
public abstract class LocksItemsLockMixin {

    //modify existing lock stats
    @WrapOperation(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lmelonslise/locks/common/init/LocksItems;add(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;"),
            remap = false
    )
    private static Item nischitweaker_modifyLocks(String name, Item item, Operation<Item> original){
        if(item instanceof LockItem && ConfigHandler.locks.lockStats.containsKey(name)) { //modify the locks contained in our config

            //modify stats
            LocksConfig.LockData data = ConfigHandler.locks.lockStats.get(name);
            ((LockItemAccessor) item).setLength(data.pins);
            ((LockItemAccessor) item).setEnchantmentValue(data.enchantability);
            ((LockItemAccessor) item).setResistance(data.resistance);

            AdditionalLocksItems.LOCKS.put(name, (LockItem) item); // keep track of existing locks so we don't register them twice
        }
        return original.call(name, item); //LocksItems.add(name, item)
    }

    @Inject(method = "<clinit>", at = @At("TAIL"), remap = false)
    private static void nischitweaker_registerAdditionalLocks(CallbackInfo ci) {
        AdditionalLocksItems.registerLocks();
    }

    @Inject(method = "register", at = @At("TAIL"), remap = false)
    private static void nischitweaker_addLocksToOreDict(CallbackInfo ci) {
        AdditionalLocksItems.LOCKS.values().forEach(item ->
                OreDictionary.registerOre("locksLock", item)
        );
    }
}
