package com.filkond.megaclock.utils;

import com.filkond.megaclock.MegaClockAPI;
import org.bukkit.ChatColor;

import java.time.ZonedDateTime;
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

    public static String formatTime(ZonedDateTime time) {
        return String.format("%s %s %s", formatTime(time.getHour()), formatTime(time.getMinute()), formatTime(time.getSecond()));
    }

    // ужас
    private static String formatTime(int time) {
        String sTime = String.valueOf(time);
        if (sTime.length() == 2)
            return sTime;

        return "0" + sTime;
    }
}
