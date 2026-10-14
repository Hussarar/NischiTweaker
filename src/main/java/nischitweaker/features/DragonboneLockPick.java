package nischitweaker.features;

import melonslise.locks.common.init.LocksCreativeTabs;
import melonslise.locks.common.item.LockPickItem;
import net.minecraft.item.Item;
import nischitweaker.config.ConfigHandler;

public class DragonboneLockPick {
    public static final Item DRAGONBONE_LOCK_PICK = new LockPickItem(ConfigHandler.locks.dragonboneLockPickDurability).setCreativeTab(LocksCreativeTabs.TAB);
}
