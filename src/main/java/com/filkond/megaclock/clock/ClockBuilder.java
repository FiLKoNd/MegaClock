package com.filkond.megaclock.clock;

import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.FontUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.BoundingBox;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ClockBuilder {
    private final boolean frame;
    private final boolean background;
    private final Location position;
    protected final ClockDirection direction;
    private final Font font;
    private final List<Material> charMaterials;
    private final List<Material> bgMaterials;

    public ClockBuilder(boolean frame, boolean background, Location position, ClockDirection direction, Font font, List<Material> charMaterials, List<Material> bgMaterials) {
        this.frame = frame;
        this.background = background;
        this.position = position;
        this.direction = direction;
        this.font = font;
        this.charMaterials = charMaterials;
        this.bgMaterials = bgMaterials;
    }

    public void setup() {
        if (background)
            buildBg();
        if (frame)
            buildFrame();
    }

    private void buildFrame() {
    }

    private void buildBg() {

    }

    public void buildChar(int index, char letter, String text) {
        var charLoc = FontUtils.getCharLocation(index, position, direction, font, text);
        var blocks = FontUtils.getBlocks(charLoc, FontUtils.getImage(String.valueOf(letter), font), direction);
        for (Block block : blocks) {
            block.setType(getRandomMaterial(charMaterials));
        }
    }

    private void clearTerritory(Location startLocation, Location endLocation) {

    }

    public Material getRandomMaterial(List<Material> materials) {
        if (materials.isEmpty())
            throw new IllegalArgumentException("materials must not be empty");

        if (materials.size() == 1)
            return materials.get(0);

        Random random = new Random();
        var materialIndex = random.nextInt(0, materials.size() - 1);
        return materials.get(materialIndex);
    }
}
