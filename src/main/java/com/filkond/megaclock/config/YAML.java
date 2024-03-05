package com.filkond.megaclock.config;

import com.filkond.megaclock.MegaClock;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YAML {
    public static YAML INSTANCE;

    private File file;
    private FileConfiguration yaml;

    public YAML(String name) {
        init(name);
    }

    public YAML(File file, FileConfiguration yaml) {
        this.file = file;
        this.yaml = yaml;
    }

    public void init(String name) {

        file = new File(MegaClock.getInstance().getDataFolder(), name);
        if (!file.exists()) {
            MegaClock.getInstance().saveResource(name, true);
        }
        yaml = YamlConfiguration.loadConfiguration(file);

    }

    public void save() {
        try {
            yaml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getYaml() {
        return yaml;
    }
}
