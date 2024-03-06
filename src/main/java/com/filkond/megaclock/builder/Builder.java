package com.filkond.megaclock.builder;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public abstract class Builder {
    private final Location position;
    private final List<Material> material;

    protected Builder(Location position, List<Material> material) {
        this.position = position;
        this.material = material;
    }

    abstract public void build();
}
