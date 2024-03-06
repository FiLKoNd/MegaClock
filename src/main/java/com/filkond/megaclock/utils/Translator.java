package com.filkond.megaclock.utils;

import com.filkond.megaclock.MegaClockAPI;
import org.bukkit.ChatColor;

import java.util.List;

public class Translator {
    public static String of(String key) {
        return colorize(MegaClockAPI.getInstance().getMessages().getYaml().getString(key));
    }

    public static List<String> ofList(String key) {
        return colorize(MegaClockAPI.getInstance().getMessages().getYaml().getStringList(key));
    }


    // легаси хуйня
    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

        public static List<String> colorize(List<String> messages) {
        return messages.stream()
                .map(Translator::colorize)
                .toList();
    }
}
