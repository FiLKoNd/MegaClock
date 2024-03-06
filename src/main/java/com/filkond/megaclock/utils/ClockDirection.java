package com.filkond.megaclock.utils;

import org.bukkit.block.BlockFace;

public enum ClockDirection {
    NORTH(BlockFace.NORTH, -1, 0),
    EAST(BlockFace.EAST, 0, -1),
    SOUTH(BlockFace.SOUTH, 1, 0),
    WEST(BlockFace.WEST, 0, 1);


    private final BlockFace face;
    private final int modVX;
    private final int modVZ;

    ClockDirection(BlockFace face, int modVX, int modVZ) {
        this.face = face;
        this.modVX = modVX;
        this.modVZ = modVZ;
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
}
