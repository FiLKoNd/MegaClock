package com.filkond.megaclock.object;

import com.filkond.megaclock.builder.ClockBuilder;
import com.filkond.megaclock.rotator.Rotator;
import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.ClockPose;
import org.bukkit.Location;
import org.bukkit.Material;

import java.time.ZonedDateTime;
import java.util.List;

public class ClockObject {
    private final String name;
    private final List<Material> materials;
    private final List<Material> backgroundMaterials;
    private final int offset;
    private final Location startLocation;
    private final ClockBuilder builder;
    private final ZonedDateTime time;

    public ClockObject(String name, List<Material> materials, List<Material> backgroundMaterials, int offset, Location startLocation, ZonedDateTime time, ClockDirection direction, ClockPose pose) {
        this.name = name;
        this.materials = materials;
        this.backgroundMaterials = backgroundMaterials;
        this.offset = offset;
        this.startLocation = startLocation;
        this.builder = new ClockBuilder(startLocation, Rotator.get(direction, pose), materials);
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
