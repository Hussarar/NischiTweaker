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
        ConfigHandler.locks.additionalLocks.forEach((name, stats) -> {
            String[] parts = stats.split(":");
            int length = Integer.parseInt(parts[0]);
            int enchVal = Integer.parseInt(parts[1]);
            float resist = Float.parseFloat(parts[2]);

            //Create the Lock
            LockItem item = new LockItem(length, enchVal, resist);
            item.setCreativeTab(LocksCreativeTabs.TAB);

            LOCKS.put(name, item);
            LocksItems.add(name, item);
        });
    }
}