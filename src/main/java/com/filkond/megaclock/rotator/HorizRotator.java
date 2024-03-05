package com.filkond.megaclock.rotator;

import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.ClockPose;
import org.bukkit.block.BlockFace;

public class HorizRotator extends Rotator {

    protected HorizRotator(ClockDirection direction) {
        super(direction, ClockPose.HORIZONTAL);
    }

    @Override
    public int getModX() {
        return direction.getModHX();
    }

    @Override
    public int getModY() {
        return direction.getModHY();
    }

    @Override
    public int getModZ() {
        return direction.getModHZ();
    }
}
