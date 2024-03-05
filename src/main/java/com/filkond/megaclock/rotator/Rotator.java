package com.filkond.megaclock.rotator;

import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.ClockPose;
import org.bukkit.block.BlockFace;

public abstract class Rotator {
    protected final ClockDirection direction;
    private final ClockPose pose;

    public Rotator(ClockDirection direction, ClockPose pose) {
        this.direction = direction;
        this.pose = pose;
    }
    public abstract int getModX();
    public abstract int getModY();
    public abstract int getModZ();

    public static Rotator get(ClockDirection direction, ClockPose pose) {
        switch (pose) {
            case VERTICAL -> {
                return new VertRotator(direction);
            }
            case HORIZONTAL -> {
                return new HorizRotator(direction);
            }
        }
        return null;
    }
}
