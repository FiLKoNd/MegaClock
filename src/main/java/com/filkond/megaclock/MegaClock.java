package com.filkond.megaclock;

import com.filkond.megaclock.commands.ClockCommand;
import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.commands.sub.CreateCommand;
import com.filkond.megaclock.tasks.ClockTask;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class MegaClock extends JavaPlugin {
    public static MegaClock INSTANCE;
    private final HashMap<String, ICommand> subcommands = new HashMap<>();

    @Override
    public void onEnable() {
        INSTANCE = this;
        setupCommands();
        new ClockTask().runTaskTimerAsynchronously(this, 20L, 0L);
    }
    private void setupCommands() {
        getCommand("clock").setExecutor(new ClockCommand());
        subcommands.put("create", new CreateCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MegaClock getInstance() {
        return INSTANCE;
    }
}
