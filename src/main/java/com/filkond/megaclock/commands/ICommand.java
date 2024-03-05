package com.filkond.megaclock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public interface ICommand extends CommandExecutor, TabCompleter {
    void execute(CommandSender sender, String[] args);
    List<String> complete(CommandSender sender, String[] args);

    @Override
    default boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, args);
        return true;
    }

    @Override
    default List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return complete(sender, args);
    }
}
