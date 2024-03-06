package com.filkond.megaclock.tasks;

import com.filkond.megaclock.MegaClockAPI;
import com.filkond.megaclock.object.ClockObject;
import org.bukkit.scheduler.BukkitRunnable;

public class ClockTask extends BukkitRunnable {
    @Override
    public void run() {
        for (ClockObject clock : MegaClockAPI.getInstance().getClocks()) {
            clock.update();
        }
    }
}
