package com.filkond.megaclock.rotator;

import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.ClockPose;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public abstract class Rotator {
    protected final ClockDirection direction;
    private final ClockPose pose;
    private int offsetX = 0;
    private int offsetY = 0;
    private int offsetZ = 0;

    public Rotator(ClockDirection direction, ClockPose pose) {
        this.direction = direction;
        this.pose = pose;
    }
    public abstract int getLineModX();
    public abstract int getLineModY();
    public abstract int getLineModZ();
    public abstract int getSideModX();
    public abstract int getSideModZ();

    public int getStartX() {
        int x = offsetX;
        offsetX = 0;
        return x;
    }

    public int getStartY() {
        int y = offsetY;
        offsetY = 0;
        return y;
    }

    public int getStartZ() {
        int z = offsetZ;
        offsetZ = 0;
        return z;
    }

    public void addStartX() {
        offsetX++;
    }

    public void addStartY() {
        offsetY++;
    }

    public void addStartZ() {
        offsetZ++;
    }

    @NotNull
    public static Rotator get(ClockDirection direction, ClockPose pose) {
        switch (pose) {
            case VERTICAL -> {
                return new VertRotator(direction);
            }
            case GROUNDED -> {
                return new GroundedRotator(direction);
            }
        }
        throw new RuntimeException("pose is strange");
    }
}
