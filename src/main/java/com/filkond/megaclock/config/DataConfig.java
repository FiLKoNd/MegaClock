package com.filkond.megaclock.config;

import org.bukkit.configuration.file.FileConfiguration;

public class DataConfig extends YAML {
    private static DataConfig INSTANCE;

    public DataConfig() {
        super("data.yml");
    }

    public static DataConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataConfig();
        }
        return INSTANCE;
    }
}
