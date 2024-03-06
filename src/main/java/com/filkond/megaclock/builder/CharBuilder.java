package com.filkond.megaclock.builder;

import com.filkond.megaclock.rotator.Rotator;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class CharBuilder extends Builder {
    protected CharBuilder(Location position, Rotator rotator, List<Material> material) {
        super(position, rotator, material);
    }

    @Override
    public void build() {

    }
}
