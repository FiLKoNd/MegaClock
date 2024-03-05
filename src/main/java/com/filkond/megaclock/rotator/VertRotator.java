package com.filkond.megaclock.rotator;

import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.ClockPose;
import org.bukkit.block.BlockFace;

public class VertRotator extends Rotator {

    protected VertRotator(ClockDirection direction) {
        super(direction, ClockPose.VERTICAL);
    }

    @Override
    public int getModX() {
        return 0;
    }

    @Override
    public int getModY() {
        return 0;
    }

    @Override
    public int getModZ() {
        return 0;
    }
}
