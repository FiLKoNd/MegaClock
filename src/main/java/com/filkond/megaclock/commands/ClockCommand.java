package com.filkond.megaclock.commands;

import com.filkond.megaclock.utils.Characters;
import com.filkond.megaclock.utils.ClockDirection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
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

        if (args.length < 3) {
            return true;
        }

        var world = player.getWorld();
        var face = player.getFacing();
        BlockFace oppositeFace = face.getOppositeFace();
        var cf = ClockDirection.getDirection(oppositeFace);
        int cx = Integer.parseInt(args[0]);
        int cy = Integer.parseInt(args[1]);
        int cz = Integer.parseInt(args[2]);
        Location location = new Location(player.getWorld(), cx, cy, cz);
        int x = location.getBlockX() + cf.getModX();
        int y = location.getBlockY();
        int z = location.getBlockZ() + cf.getModZ();
        for (boolean[] block : Characters.ONE.getBlocks()) {
            y--;
            for (boolean b : block) {
                x -= cf.getModX();
                z -= cf.getModZ();
                if (b) {
                    world.getBlockAt(x, y, z).setType(Material.STONE);
                }
            }
            x = location.getBlockX() + cf.getModX();
            z = location.getBlockZ() + cf.getModZ();
        }

        return true;
    }
}
