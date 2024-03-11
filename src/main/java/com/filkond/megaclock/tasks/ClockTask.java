package com.filkond.megaclock.tasks;

import com.filkond.megaclock.MegaClockAPI;
import com.filkond.megaclock.clock.Clock;
import org.bukkit.scheduler.BukkitRunnable;

public class ClockTask extends BukkitRunnable {
    @Override
    public void run() {
        for (Clock clock : MegaClockAPI.getInstance().getClocks()) {
            clock.update(false);
        }
    }
}
