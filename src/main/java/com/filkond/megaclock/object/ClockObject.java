package com.filkond.megaclock.object;

import com.filkond.megaclock.builder.ClockBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.checkerframework.common.value.qual.ArrayLen;

import java.time.ZonedDateTime;
import java.util.List;

public class ClockObject {
    private final String name;
    private final Location startLocation;
    private final ClockBuilder builder;
    @ArrayLen(10)
    private char[] timeChars; // 12:12:12PM
    private final ZonedDateTime zonedDateTime;

    public ClockObject(String name, Location startLocation, ZonedDateTime zonedDateTime, ClockBuilder builder) {
        this.name = name;
        this.startLocation = startLocation;
        this.builder = builder;
        this.zonedDateTime = zonedDateTime;
        update();
    }

    public void update() {

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

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
