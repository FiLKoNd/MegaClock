package com.filkond.megaclock.rotator;

import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.ClockPose;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class GroundedRotator extends Rotator {

    protected GroundedRotator(ClockDirection direction) {
        super(direction, ClockPose.GROUNDED);
    }

    @Override
    public int getLineModX() {
        return direction.getModVX();
    }

    @Override
    public int getLineModY() {
        return 0;
    }

    @Override
    public int getLineModZ() {
        return direction.getModVX();
    }

    @Override
    public int getSideModX() {
        return direction.getModGLX();
    }

    @Override
    public int getSideModZ() {
        return direction.getModGLZ();
    }
}
