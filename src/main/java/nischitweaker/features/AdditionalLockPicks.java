package nischitweaker.features;

import melonslise.locks.common.init.LocksCreativeTabs;
import melonslise.locks.common.init.LocksItems;
import melonslise.locks.common.item.LockPickItem;
import nischitweaker.config.ConfigHandler;

import java.util.HashMap;
import java.util.Map;

public class AdditionalLockPicks {
    // All registered lockpicks (existing + additional) mapped by their item path (so without locks:)
    public static final Map<String, LockPickItem> LOCKPICKS = new HashMap<>();

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
}
