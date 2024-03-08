package com.filkond.megaclock.object;

import com.filkond.megaclock.builder.ClockBuilder;
import com.filkond.megaclock.utils.ClockDirection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.checkerframework.common.value.qual.ArrayLen;

import java.time.ZonedDateTime;
import java.util.List;

public class ClockObject {
    private final String name;
    private final List<Material> materials;
    private final List<Material> backgroundMaterials;
    private final int offset;
    private final Location startLocation;
    private final ClockBuilder builder;
    @ArrayLen(10)
    private char[] timeChars; // 12:12:12PM
    private final ZonedDateTime zonedDateTime;

    public ClockObject(String name, List<Material> materials, List<Material> backgroundMaterials, int offset, Location startLocation, ZonedDateTime zonedDateTime, ClockDirection direction) {
        this.name = name;
        this.materials = materials;
        this.backgroundMaterials = backgroundMaterials;
        this.offset = offset;
        this.startLocation = startLocation;
        this.builder = new ClockBuilder(startLocation, materials);
        this.zonedDateTime = zonedDateTime;
        update();
    }

    public void update() {

    }

    public String getName() {
        return name;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public int getOffset() {
        return offset;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
