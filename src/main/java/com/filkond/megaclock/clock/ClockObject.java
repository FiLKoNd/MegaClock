package com.filkond.megaclock.clock;

import com.filkond.megaclock.utils.Translator;
import org.bukkit.Location;
import org.checkerframework.common.value.qual.ArrayLen;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ClockObject {
    private final String name;
    private final Location startLocation;
    private final ClockBuilder builder;
    @ArrayLen(8)
    private String timeText = "11:11:11"; // 12:12:12
    private final ZoneId zoneId;

    public ClockObject(String name, Location startLocation, ZoneId zoneId, ClockBuilder builder) {
        this.name = name;
        this.startLocation = startLocation;
        this.builder = builder;
        this.zoneId = zoneId;
        builder.setup();
    }

    public void update() {
        ZonedDateTime time = ZonedDateTime.now(zoneId);
        String formattedTime = Translator.formatTime(time);
        for (int i = 0; i < timeText.length(); i++) {
            if (timeText.charAt(i) == formattedTime.charAt(i)) {
                continue;
            }

            builder.buildChar(i, formattedTime.charAt(i), timeText);
        }
        timeText = formattedTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof ClockObject clock))
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
}
