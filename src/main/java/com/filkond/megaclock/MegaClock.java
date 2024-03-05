package com.filkond.megaclock;

import com.filkond.megaclock.commands.ClockCommand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class MegaClock extends JavaPlugin {
    public static MegaClock INSTANCE;

    @Override
    public void onEnable() {
        getCommand("clock").setExecutor(new ClockCommand());
    }

    private void setupConfigs() {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MegaClock getInstance() {
        return INSTANCE;
    }
}
