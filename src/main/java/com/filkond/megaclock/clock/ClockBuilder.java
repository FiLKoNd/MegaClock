package com.filkond.megaclock.clock;

import com.filkond.megaclock.MegaClock;
import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.FontUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
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
    private final Random random = new Random();


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

    private void clearTerritory(Location startLocation, Location endLocation) {
        int minX = Math.min(startLocation.getBlockX(), endLocation.getBlockX());
        int minY = Math.min(startLocation.getBlockY(), endLocation.getBlockY());
        int minZ = Math.min(startLocation.getBlockZ(), endLocation.getBlockZ());
        int maxX = Math.max(startLocation.getBlockX(), endLocation.getBlockX());
        int maxY = Math.max(startLocation.getBlockY(), endLocation.getBlockY());
        int maxZ = Math.max(startLocation.getBlockZ(), endLocation.getBlockZ());
        var world = position.getWorld();
        assert world != null;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    world.getBlockAt(x, y, z).setType(Material.AIR);
                }
            }
        }
    }

    public void buildChar(int index, char letter, String text) {
        var charLoc = FontUtils.getCharLocation(index, position, direction, font, text);
        var blocks = FontUtils.getBlocks(charLoc, FontUtils.getImage(String.valueOf(letter), font), direction, true);
        var allBlocks = FontUtils.getBlocks(charLoc, FontUtils.getImage(String.valueOf(letter), font), direction, false);
        for (Block block : allBlocks) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    block.setType(Material.AIR);
                }
            }.runTask(MegaClock.getInstance());
        }
        for (Block block : blocks) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    block.setType(getRandomMaterial(charMaterials));
                }
            }.runTask(MegaClock.getInstance());
        }
    }

    public Material getRandomMaterial(List<Material> materials) {
        if (materials.isEmpty())
            throw new IllegalArgumentException("materials must not be empty");

        if (materials.size() == 1)
            return materials.get(0);

        var materialIndex = random.nextInt(0, materials.size() - 1);
        return materials.get(materialIndex);
    }
}
