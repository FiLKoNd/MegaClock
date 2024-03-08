package com.filkond.megaclock.builder;

import com.filkond.megaclock.utils.ClockDirection;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class ClockBuilder extends Builder {
    private final boolean background;
    private final List<Material> charMaterials;
    private final List<Material> bgMaterials;
    private final boolean frame;

    public ClockBuilder(Location position, ClockDirection direction, boolean background, boolean frame, List<Material> charMaterials, List<Material> bgMaterials) {
        super(position, direction, charMaterials);
        this.background = background;
        this.charMaterials = charMaterials;
        this.bgMaterials = bgMaterials;
        this.frame = frame;
    }

    @Override
    public void build() {
        if (background) {

        }

        if (frame) {

        }
    }

    public void buildChar(int pos, char letter, Location location) {
        var builder = new CharBuilder(location, direction, material, letter);
        builder.build();
    }
}
