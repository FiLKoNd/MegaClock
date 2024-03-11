package com.filkond.megaclock;

import com.filkond.megaclock.commands.ClockCommand;
import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.commands.sub.CreateCommand;
import com.filkond.megaclock.commands.sub.DestroyCommand;
import com.filkond.megaclock.commands.sub.TimezoneCommand;
import com.filkond.megaclock.tasks.ClockTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class MegaClock extends JavaPlugin {
    public static MegaClock INSTANCE;
    private final HashMap<String, ICommand> subcommands = new HashMap<>();

    public HashMap<String, ICommand> getSubcommands() {
        return subcommands;
    }

    @Override
    public void onEnable() {
        saveResource("fonts/minecraft-numbers.ttf", false);
        INSTANCE = this;
        setupCommands();
        new ClockTask().runTaskTimerAsynchronously(this, 0L, 20L);
    }

    private void setupCommands() {
        getCommand("clock").setExecutor(new ClockCommand());
        subcommands.put("create", new CreateCommand());
        subcommands.put("timezone", new TimezoneCommand());
        subcommands.put("destroy", new DestroyCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MegaClock getInstance() {
        return INSTANCE;
    }
}
