package com.filkond.megaclock.builder;

import com.filkond.megaclock.utils.ClockDirection;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public abstract class Builder {
    private final Location position;
    protected final ClockDirection direction;
    protected final List<Material> material;

    protected Builder(Location position, ClockDirection direction, List<Material> material) {
        this.position = position;
        this.direction = direction;
        this.material = material;
    }

    abstract public void build();
}
