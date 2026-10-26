package nischitweaker.features;

import melonslise.locks.common.init.LocksCreativeTabs;
import melonslise.locks.common.init.LocksItems;
import melonslise.locks.common.item.LockItem;
import melonslise.locks.common.item.LockPickItem;
import nischitweaker.config.ConfigHandler;
import nischitweaker.config.folders.LocksConfig;

import java.util.HashMap;
import java.util.Map;

public class AdditionalLocksItems {
    // All registered lockpicks (existing + additional) mapped by their item path (so without locks:)
    public static final Map<String, LockPickItem> LOCKPICKS = new HashMap<>();

    // Same for locks
    public static final Map<String, LockItem> LOCKS = new HashMap<>();

    public static void registerLockPicks() {
        //Register additional lockpicks (by this point LOCKPICKS already contains all existing ones)
        ConfigHandler.locks.lockPickStrengths.entrySet().stream()
                .filter(e -> !LOCKPICKS.containsKey(e.getKey())) //filter out already registered ones
                .forEach(e -> {
                    String name = e.getKey();
                    float strength = e.getValue();
                    LockPickItem item = new LockPickItem(strength);
                    item.setCreativeTab(LocksCreativeTabs.TAB);
                    LOCKPICKS.put(name, item);
                    LocksItems.add(name, item);
                });
    }

    public static void registerLocks() {
        ConfigHandler.locks.lockStats.entrySet().stream()
                .filter(e -> !LOCKS.containsKey(e.getKey()))
                .forEach(e -> {
                    String name = e.getKey();
                    LocksConfig.LockData data = e.getValue();
                    LockItem item = new LockItem(data.pins, data.enchantability, data.resistance);
                    item.setCreativeTab(LocksCreativeTabs.TAB);
                    LOCKS.put(name, item);
                    LocksItems.add(name, item);
                });
    }
}
