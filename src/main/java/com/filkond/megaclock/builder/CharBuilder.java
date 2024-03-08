package com.filkond.megaclock.builder;

import com.filkond.megaclock.utils.ClockDirection;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class CharBuilder extends Builder {
    private final char letter;
    protected CharBuilder(Location position, ClockDirection direction, List<Material> material, char letter) {
        super(position, direction, material);
        this.letter = letter;
    }

    @Override
    public void build() {

    }
}
