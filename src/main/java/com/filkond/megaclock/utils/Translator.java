package com.filkond.megaclock.utils;

import org.bukkit.ChatColor;

public class Translator {
    public String of(String key) {
        return null;
    }

    public String colorize(String msg) { // легаси хуйня
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
