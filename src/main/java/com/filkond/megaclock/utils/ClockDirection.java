package com.filkond.megaclock.utils;

import org.bukkit.block.BlockFace;

public enum ClockDirection {
    NORTH(BlockFace.NORTH, -1, 0, -1, 0, 0, -1),
    EAST(BlockFace.EAST, 0, -1, 0, -1, 1, 0),
    SOUTH(BlockFace.SOUTH, 1, 0, 1, 0, 0, 1),
    WEST(BlockFace.WEST, 0, 1, 0, 1, -1, 0);


    private final BlockFace face;
    private final int modVX;
    private final int modVZ;
    private final int modGLX; // grounded
    private final int modGLZ; // grounded
    private final int modGSX; // grounded
    private final int modGSZ; // grounded

    ClockDirection(BlockFace face, int modVX, int modVZ, int modGLX, int modGLZ, int modGSX, int modGSZ) {
        this.face = face;
        this.modVX = modVX;
        this.modVZ = modVZ;
        this.modGLX = modGLX;
        this.modGLZ = modGLZ;
        this.modGSX = modGSX;
        this.modGSZ = modGSZ;
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

    public int getModVZ() {
        return modVZ;
    }

    public int getModGLX() {
        return modGLX;
    }

    public int getModGLZ() {
        return modGLZ;
    }

    public int getModGSX() {
        return modGSX;
    }

    public int getModGSZ() {
        return modGSZ;
    }
}
