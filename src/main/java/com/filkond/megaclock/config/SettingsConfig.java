package com.filkond.megaclock.config;

public class SettingsConfig extends YAML {
    private static SettingsConfig INSTANCE;

    public SettingsConfig() {
        super("settings.yml");
    }

    public static SettingsConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SettingsConfig();
        }
        return INSTANCE;
    }
}
