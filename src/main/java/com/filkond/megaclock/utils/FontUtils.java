package com.filkond.megaclock.utils;

import com.filkond.megaclock.MegaClock;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FontUtils {
    public static final Font defaultFont;

    static {
        try {
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, new File(MegaClock.getInstance().getDataFolder(), "fonts/minecraft-numbers.ttf"))
                    .deriveFont(5F);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static List<Block> getBlocks(Location loc, BufferedImage image, ClockDirection direction) {
        List<Block> out = new ArrayList<>();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb & 0xff0000) >> 16;
                int green = (rgb & 0xff00) >> 8;
                int blue = rgb & 0xff;
                if (red != 0 || green != 0 || blue != 0) {
                    int xLoc = x * direction.getModVX();
                    int zLoc = x * direction.getModVZ();
                    Block block = loc.clone().add(xLoc, -y, zLoc).getBlock();
                    out.add(block);
                }
            }
        }
        return out;
    }

    public static Location getCharLocation(int index, Location location, ClockDirection direction, Font font, String text) {
        if (index < 0 || index >= text.length()) {
            throw new IllegalArgumentException("Index must been less then text length");
        }


        var fm = FontUtils.getFontMetrics(font);
        int charWidth = fm.charWidth(text.charAt(index));


        int x = index * charWidth;
        int xLoc = x * direction.getModVX();
        int zLoc = x * direction.getModVZ();

        var loc = location.clone();
        loc.setX(loc.getX() + xLoc);
        loc.setZ(loc.getZ() + zLoc);

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
}
