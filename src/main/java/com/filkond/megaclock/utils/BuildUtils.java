package com.filkond.megaclock.utils;

import com.filkond.megaclock.MegaClock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BuildUtils {

    public static BufferedImage getImage(String text, Font font) {
        var fm = getFontMetrics(font);
        var img = new BufferedImage(fm.stringWidth(text), fm.getHeight(), BufferedImage.TYPE_INT_RGB);
        var g2d = img.createGraphics();
        g2d = img.createGraphics();
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
        return img;
    }

    public static List<Block> getBlocks(Location loc, BufferedImage image, ClockDirection direction, boolean ignoreEmpty) {
        List<Block> out = new ArrayList<>();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb & 0xff0000) >> 16;
                int green = (rgb & 0xff00) >> 8;
                int blue = rgb & 0xff;
                if (red == 0 && green == 0 && blue == 0 && ignoreEmpty) {
                    continue;
                }
                int xLoc = x * direction.getModVX();
                int zLoc = x * direction.getModVZ();
                Block block = loc.clone().add(xLoc, -y, zLoc).getBlock();
                out.add(block);

            }
        }
        return out;
    }

    public static Location getCharLocation(int index, Location location, ClockDirection direction, Font font, String text) {
        if (index < 0 || index >= text.length()) {
            throw new IllegalArgumentException("Index must been less then text length");
        }

        var fm = BuildUtils.getFontMetrics(font);
        int charWidth = fm.charWidth(text.charAt(index));


        int x = index * charWidth;
        int xLoc = x * direction.getModVX();
        int zLoc = x * direction.getModVZ();

        var loc = location.clone();
        loc.add(xLoc, 0, zLoc);
        return loc;
    }

    private static FontMetrics getFontMetrics(Font font) {
        var bimg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        var g2d = bimg.createGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.dispose();
        return fm;
    }

    public static Location getTextEnd(Location startLoc, String text, Font font, ClockDirection direction) {
        FontMetrics fm = getFontMetrics(font);
        var lastCharWidth = fm.charWidth(text.charAt(text.length() - 1)) / 4;
        var textWidth = fm.stringWidth(text) - lastCharWidth - 1;

        var x = textWidth * direction.getModVX();
        var y = -fm.getAscent() + 1;
        var z = textWidth * direction.getModVZ();

        Location loc = startLoc.clone();
        loc.add(x, y, z);
        return loc;
    }
}
