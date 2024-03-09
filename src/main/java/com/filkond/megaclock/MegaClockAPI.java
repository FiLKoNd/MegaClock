package com.filkond.megaclock;

import com.filkond.megaclock.clock.ClockBuilder;
import com.filkond.megaclock.config.DataConfig;
import com.filkond.megaclock.config.MessagesConfig;
import com.filkond.megaclock.config.SettingsConfig;
import com.filkond.megaclock.clock.ClockObject;
import com.filkond.megaclock.utils.ClockDirection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.awt.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public final class MegaClockAPI {
    public static MegaClockAPI INSTANCE;
    private MessagesConfig messages;
    private SettingsConfig settings;
    private DataConfig data;
    private final List<ClockObject> clocks = new ArrayList<>();

    @Nullable
    public ClockObject getClock(String name) {
        for (ClockObject clock : clocks) {
            if (clock.getName().equalsIgnoreCase(name)) {
                return clock;
            }
        }
        return null;
    }

    public ClockObject createClock(String name, Location location, ZoneId zoneId, ClockDirection direction, Font font, boolean bg, boolean frame) {
        var builder = new ClockBuilder(frame, bg, location, direction, font, List.of(Material.STONE), List.of(Material.COBBLESTONE));
        var clock = new ClockObject(name, location, zoneId, builder);
        clocks.add(clock);
        return clock;
    }

    public MessagesConfig getMessages() {
        return messages;
    }

    public SettingsConfig getSettings() {
        return settings;
    }

    public DataConfig getData() {
        return data;
    }

    public List<ClockObject> getClocks() {
        return clocks;
    }

    @NotNull
    public static MegaClockAPI getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MegaClockAPI();
            INSTANCE.messages = new MessagesConfig();
            INSTANCE.settings = new SettingsConfig();
            INSTANCE.data = new DataConfig();
        }
        return INSTANCE;
    }
}
