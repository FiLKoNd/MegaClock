package com.filkond.megaclock;

import com.filkond.megaclock.commands.ClockCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MegaClock extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("clock").setExecutor(new ClockCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
