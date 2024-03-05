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

import java.util.List;

public class ClockCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        {
            if (!(sender instanceof Player player)) {
                return;
            }

            if (args.length < 3) {
                return;
            }

            var world = player.getWorld();
            var face = player.getFacing();
            ClockDirection cf = ClockDirection.getDirection(face.getOppositeFace());
            int cx = Integer.parseInt(args[0]);
            int cy = Integer.parseInt(args[1]);
            int cz = Integer.parseInt(args[2]);
            Location location = new Location(player.getWorld(), cx, cy, cz);
            int x = location.getBlockX() + cf.getModVX();
            int y = location.getBlockY();
            int z = location.getBlockZ() + cf.getModVZ();
            for (boolean[] block : Characters.ONE.getBlocks()) {
                for (boolean b : block) {
                    x -= cf.getModVX();
                    z -= cf.getModVZ();
                    if (b) {
                        world.getBlockAt(x, y, z).setType(Material.STONE);
                    }
                }
                x = location.getBlockX() + cf.getModVX();
                z = location.getBlockZ() + cf.getModVZ();
            }
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        return null;
    }
}
