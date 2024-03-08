package com.filkond.megaclock.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FontUtils {
    public static BufferedImage getImage(String text, Font font) {
        var fm = getFontMetrics(text, font);
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

    private static FontMetrics getFontMetrics(String text, Font font) {
        var bimg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        var g2d = bimg.createGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.dispose();
        return fm;
    }
}
