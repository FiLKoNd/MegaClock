package com.filkond.megaclock.clock;

import com.filkond.megaclock.MegaClock;
import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.BuildUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ClockBuilder implements ConfigurationSerializable {
    private final boolean frame;
    private final boolean background;
    private final Location position;
    protected final ClockDirection direction;
    private final Font font;
    private List<Material> charMaterials;
    private List<Material> bgMaterials;
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

    public void setup(String time) {
        if (background)
            buildBg(time);
        if (frame)
            buildFrame(time);
    }

    private void buildFrame(String time) {

    }

    private void buildBg(String time) {
        fill(getLeftBorder().subtract(direction.getFace().getModX(), 0, direction.getFace().getModZ()),
                getRightBorder(time).subtract(direction.getFace().getModX(), 0, direction.getFace().getModZ()),
                bgMaterials);
    }

    private Location getLeftBorder() {
        return position.clone().add(-direction.getModVX(), 1, -direction.getModVZ());
    }

    private Location getRightBorder(String time){
        return BuildUtils.getTextEnd(position, time, font, direction)
                .add(direction.getModVX(), -1, direction.getModVZ()); // фулл похуй
    }

    private void fill(Location startLocation, Location endLocation, List<Material> materials) {
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
                    world.getBlockAt(x, y, z).setType(getRandomMaterial(materials));
                }
            }
        }
    }

    public void buildChar(int index, char letter, String text) {
        var charLoc = BuildUtils.getCharLocation(index, position, direction, font, text);
        var blocks = BuildUtils.getBlocks(charLoc, BuildUtils.getImage(String.valueOf(letter), font), direction, true);
        var allBlocks = BuildUtils.getBlocks(charLoc, BuildUtils.getImage(String.valueOf(letter), font), direction, false);
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Block block : allBlocks) {
                    block.setType(Material.AIR);
                }
                for (Block block : blocks) {
                    block.setType(getRandomMaterial(charMaterials));
                }
            }
        }.runTask(MegaClock.getInstance());
    }

    public Material getRandomMaterial(List<Material> materials) {
        if (materials.isEmpty())
            throw new IllegalArgumentException("materials must not be empty");

        if (materials.size() == 1)
            return materials.get(0);

        var materialIndex = random.nextInt(0, materials.size() - 1);
        return materials.get(materialIndex);
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    public static Clock deserialize(Map<String, Object> serializedMap) {
        return null;
    }

    public List<Material> getCharMaterials() {
        return charMaterials;
    }

    public void setCharMaterials(List<Material> charMaterials) {
        this.charMaterials = charMaterials;
    }

    public List<Material> getBgMaterials() {
        return bgMaterials;
    }

    public void setBgMaterials(List<Material> bgMaterials) {
        this.bgMaterials = bgMaterials;
    }
}
