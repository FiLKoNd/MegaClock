package com.filkond.megaclock.clock;

import com.filkond.megaclock.utils.Translator;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.checkerframework.common.value.qual.ArrayLen;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

public class Clock implements ConfigurationSerializable {
    private final String name;
    private final Location startLocation;
    private final ClockBuilder builder;
    @ArrayLen(8)
    private String timeText = "12:12:12"; // 12:12:12
    private ZoneId zoneId;


    public Clock(String name, Location startLocation, ZoneId zoneId, ClockBuilder builder) {
        this.name = name;
        this.startLocation = startLocation;
        this.builder = builder;
        this.zoneId = zoneId;
        update(true);
        builder.setup(timeText);
    }

    public void update(boolean force) {
        ZonedDateTime time = ZonedDateTime.now(zoneId);
        String formattedTime = Translator.formatTime(time);
        for (int i = 0; i < timeText.length(); i++) {
            if ((timeText.charAt(i) == formattedTime.charAt(i)) && !force) {
                continue;
            }

            builder.buildChar(i, formattedTime.charAt(i), timeText);
        }
        timeText = formattedTime;
    }

    public void destroy() {
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    public static Clock deserialize(Map<String, Object> serializedMap) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Clock clock))
            return false;
        return clock.name.equals(this.name);
    }

    public String getName() {
        return name;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public ClockBuilder getBuilder() {
        return builder;
    }

    public String getTimeText() {
        return timeText;
    }
}
