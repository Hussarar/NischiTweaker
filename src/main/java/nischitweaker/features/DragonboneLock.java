package nischitweaker.features;

import melonslise.locks.common.init.LocksCreativeTabs;
import melonslise.locks.common.init.LocksItems;
import melonslise.locks.common.item.LockItem;
import nischitweaker.config.ConfigHandler;

import java.util.HashMap;
import java.util.Map;

public class DragonboneLock {
    public static final Map<String, LockItem> LOCKS = new HashMap<>();

    public static void registerLocks() {
        ConfigHandler.locks.additionalLocks.forEach((name, data) -> {
            LockItem item = new LockItem(data.pins, data.enchantability, data.resistance);
            item.setCreativeTab(LocksCreativeTabs.TAB);

            LOCKS.put(name, item);
            LocksItems.add(name, item);
        });
    }
}