package com.filkond.megaclock.rotator;

import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.ClockPose;

public class VertRotator extends Rotator {

    protected VertRotator(ClockDirection direction) {
        super(direction, ClockPose.VERTICAL);
    }

    @Override
    public int getLineModX() {
        return direction.getModVX();
    }

    @Override
    public int getLineModY() {
        return -1;
    }

    @Override
    public int getLineModZ() {
        return direction.getModVX();
    }

    @Override
    public int getSideModX() {
        return direction.getModGSX();
    }

    @Override
    public int getSideModZ() {
        return direction.getModGSZ();
    }
}
