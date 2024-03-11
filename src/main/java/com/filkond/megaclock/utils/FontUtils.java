package com.filkond.megaclock.utils;

import com.filkond.megaclock.MegaClock;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class FontUtils {
    public static final Font defaultFont;
    private static final Pattern TTF_PATTERN = Pattern.compile("\\D\\.ttf");
    private static final HashMap<String, Font> FONT_CACHE = new HashMap<>();
    private static final Pattern URL_PATTERN = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");
    static {
        try {
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, new File(MegaClock.getInstance().getDataFolder(), "fonts/minecraft-numbers.ttf"))
                    .deriveFont(5F);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static Font getFont(String name, int size) {
        if (TTF_PATTERN.matcher(name).matches()) {
            if (FONT_CACHE.containsKey(name)) {
                return FONT_CACHE.get(name);
            } else {
                return createFont(name);
            }
        }
        return new Font(name, Font.PLAIN, size);
    }

    @Nullable
    private static Font createFont(String path) {
        try {
            var fontFile = new File(MegaClock.getInstance().getDataFolder(), path);
            var font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            FONT_CACHE.put(path, font);
            return font;
        } catch (FontFormatException | IOException ignored) {
        }
        return null;
    }

}
