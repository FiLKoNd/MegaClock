package com.filkond.megaclock.commands;

import com.filkond.megaclock.utils.Numbers;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        var world = player.getWorld();
        int x = player.getBedLocation().getBlockX() - 1;
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        for (boolean[] block : Numbers.ONE.getBlocks()) {
            y--;
            for (boolean b : block) {
                x++;
                if (b) {
                    world.getBlockAt(x, y, z).setType(Material.STONE);
                }
            }
            x = player.getBedLocation().getBlockX() - 1;
        }

        return true;
    }
}
