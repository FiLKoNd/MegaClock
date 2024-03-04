package com.filkond.megaclock.utils;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import javax.annotation.Nullable;

@Getter
public enum ClockDirection {
    NORTH(BlockFace.NORTH, 1, 0, 0),
    EAST(BlockFace.EAST, 0, 0, 1),
    SOUTH(BlockFace.SOUTH, -1, 0, 0),
    WEST(BlockFace.WEST, 0, 0, -1),
    NORTH_EAST(NORTH, EAST, BlockFace.NORTH_EAST),
    NORTH_WEST(NORTH, WEST, BlockFace.NORTH_WEST),
    SOUTH_EAST(SOUTH, EAST, BlockFace.SOUTH_EAST),
    SOUTH_WEST(SOUTH, WEST, BlockFace.SOUTH_WEST)
    ;


    private final BlockFace face;
    private final int modX;
    private final int modY;
    private final int modZ;

    ClockDirection(BlockFace face, int modX, int modY, int modZ) {
        this.face = face;
        this.modX = modX;
        this.modY = modY;
        this.modZ = modZ;
    }

    ClockDirection(final ClockDirection face1, final ClockDirection face2, BlockFace blockFace) {
        this.modX = face1.getModX() + face2.getModX();
        this.modY = face1.getModY() + face2.getModY();
        this.modZ = face1.getModZ() + face2.getModZ();
        this.face = blockFace;
    }

    @Nullable
    public static ClockDirection getDirection(BlockFace face) {
        for (ClockDirection value : values()) {
            if (value.face.equals(face))
                return value;
        }
        return null;
    }
}
