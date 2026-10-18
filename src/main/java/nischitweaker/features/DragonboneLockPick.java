package nischitweaker.features;

import melonslise.locks.common.init.LocksCreativeTabs;
import melonslise.locks.common.init.LocksItems;
import melonslise.locks.common.item.LockPickItem;
import nischitweaker.config.ConfigHandler;

import java.util.HashMap;
import java.util.Map;

public class DragonboneLockPick {
    public static final Map<String, LockPickItem> LOCKPICKS = new HashMap<>();

    public static void registerLockPicks() {
        ConfigHandler.locks.additionalLockpicks.forEach((name, strength) -> {
            LockPickItem item = new LockPickItem(strength);
            item.setCreativeTab(LocksCreativeTabs.TAB);
            LOCKPICKS.put(name, item);
            LocksItems.add(name, item);
        });
    }
}
