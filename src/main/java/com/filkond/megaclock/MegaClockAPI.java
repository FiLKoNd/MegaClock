package com.filkond.megaclock;

import org.bukkit.plugin.java.JavaPlugin;

public final class MegaClockAPI {
    public static MegaClockAPI instance;

    public static MegaClockAPI getInstance() {
        if (instance == null) {
            instance = new MegaClockAPI();
        }
        return instance;
    }


}
