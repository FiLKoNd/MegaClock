package com.filkond.megaclock.commands;

import com.filkond.megaclock.MegaClock;
import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.FontUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ClockCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        int cx = player.getLocation().getBlockX();
        int cy = player.getLocation().getBlockY();
        int cz = player.getLocation().getBlockZ();
        try {
            cx = Integer.parseInt(args[0]);
            cy = Integer.parseInt(args[1]);
            cz = Integer.parseInt(args[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
        }

        var face = player.getFacing();
        var clockDirection = ClockDirection.getDirection(face.getOppositeFace());
        Location location = new Location(player.getWorld(), cx, cy, cz);
//        Font font = new Font("Arial", Font.PLAIN, 18);
        Font font = FontUtils.defaultFont;
        var bl = FontUtils.getBlocks(location, FontUtils.getImage("123", font), clockDirection, true);
        for (Block block : bl) {
            block.setType(Material.STONE);
        }
        var charLoc = FontUtils.getCharLocation(1, location, clockDirection, font, "123");
        var charBlocks = FontUtils.getBlocks(charLoc, FontUtils.getImage("9", font), clockDirection, true);
        for (Block charBlock : charBlocks) {
            charBlock.setType(Material.REDSTONE_BLOCK);
        }
        var end = FontUtils.getTextEnd(location, "123", font, clockDirection);
        end.getBlock().setType(Material.DIAMOND_BLOCK);
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        return null;
    }
}
