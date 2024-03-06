package com.filkond.megaclock.builder;

import com.filkond.megaclock.rotator.Rotator;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public abstract class Builder {
    private final Location position;
    private final Rotator rotator;
    private final List<Material> material;

    protected Builder(Location position, Rotator rotator, List<Material> material) {
        this.position = position;
        this.rotator = rotator;
        this.material = material;
    }

    abstract public void build();
}
