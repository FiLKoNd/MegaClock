package com.filkond.megaclock.object;

import com.filkond.megaclock.builder.Builder;
import com.filkond.megaclock.builder.ClockBuilder;
import org.bukkit.Location;
import org.bukkit.Material;

import java.time.ZonedDateTime;
import java.util.List;

public class ClockObject {
    private final String name;
    private final List<Material> materials;
    private final int offset;
    private final Location startLocation;
    private final ClockBuilder builder;
    private final ZonedDateTime time;

    public ClockObject(String name, List<Material> materials, int offset, Location startLocation, ZonedDateTime time) {
        this.name = name;
        this.materials = materials;
        this.offset = offset;
        this.startLocation = startLocation;
        this.builder = new ClockBuilder(startLocation, materials);
        this.time = time;
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

    public ZonedDateTime getTime() {
        return time;
    }
}
