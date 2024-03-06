package com.filkond.megaclock.commands;

import com.filkond.megaclock.rotator.Rotator;
import com.filkond.megaclock.utils.Characters;
import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.ClockPose;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ClockCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        if (args.length < 3) {
            return;
        }

        var world = player.getWorld();
        var face = player.getFacing();
        ClockDirection clockDirection = ClockDirection.getDirection(face.getOppositeFace());
        var rotator = Rotator.get(clockDirection, ClockPose.GROUNDED);
        int cx = Integer.parseInt(args[0]);
        int cy = Integer.parseInt(args[1]);
        int cz = Integer.parseInt(args[2]);
        Location location = new Location(player.getWorld(), cx, cy, cz);
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        for (boolean[] block : Characters.ONE.getBlocks()) {
            x += rotator.getLineModX(); // 0
            y += rotator.getLineModY(); // -1
            z += rotator.getLineModZ(); // 0
            for (boolean b : block) {
                x += rotator.getSideModX();
                z += rotator.getSideModZ();
                if (b) {
                    world.getBlockAt(x, y, z).setType(Material.STONE);
                }
            }
            x = rotator.getStartX();
            y = rotator.getStartY();
            z = rotator.getStartZ();
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        return null;
    }
}
