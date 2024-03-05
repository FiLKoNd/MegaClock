package com.filkond.megaclock.utils;

import org.bukkit.block.BlockFace;

public enum ClockDirection {
    NORTH(BlockFace.NORTH, 1, 1, 0, 1, 0, 1),
    EAST(BlockFace.EAST, 0, 1, 1, -1, 0, 1),
    SOUTH(BlockFace.SOUTH, -1, 1, 0, -1, 0, -1),
    WEST(BlockFace.WEST, 0, 1, -1, 1, 0, -1)
    ;


    private final BlockFace face;
    private final int modVX;
    private final int modVY;
    private final int modVZ;
    private final int modHX;
    private final int modHY;
    private final int modHZ;

    ClockDirection(BlockFace face, int modVX, int modVY, int modVZ, int modHX, int modHY, int modHZ) {
        this.face = face;
        this.modVX = modVX;
        this.modVY = modVY;
        this.modVZ = modVZ;
        this.modHX = modHX;
        this.modHY = modHY;
        this.modHZ = modHZ;
    }

    public static ClockDirection getDirection(BlockFace face) throws IllegalArgumentException {
        for (ClockDirection value : values()) {
            if (value.face.equals(face))
                return value;
        }
        throw new IllegalArgumentException("Неверное направление.");
    }

    public BlockFace getFace() {
        return face;
    }

    public int getModVX() {
        return modVX;
    }

    public int getModVY() {
        return modVY;
    }

    public int getModVZ() {
        return modVZ;
    }

    public int getModHX() {
        return modHX;
    }

    public int getModHY() {
        return modHY;
    }

    public int getModHZ() {
        return modHZ;
    }
}
