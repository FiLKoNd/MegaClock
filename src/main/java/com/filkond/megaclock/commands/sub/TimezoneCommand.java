package com.filkond.megaclock.commands.sub;

import com.filkond.megaclock.MegaClockAPI;
import com.filkond.megaclock.clock.Clock;
import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.utils.Translator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.time.ZoneId;
import java.time.zone.ZoneRulesException;
import java.util.List;

public class TimezoneCommand implements ICommand {
    @Override // /clock timezone <Название> <TIMEZONE>
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        if (args.length < 3) {
            player.sendMessage(Translator.of("not-enough-args"));
            return;
        }

        var clock = MegaClockAPI.getInstance().getClock(args[1]);
        if (clock == null) {
            player.sendMessage(Translator.of("unknown-clock"));
            return;
        }

        ZoneId zoneId;
        try {
            zoneId = ZoneId.of(args[2]);
        } catch (ZoneRulesException e) {
            player.sendMessage("unknown-timezone");
            return;
        }

        clock.setZoneId(zoneId);
        player.sendMessage(Translator.of("successful-change-timezone"));
    }

    @Override
    @Nullable
    public List<String> complete(CommandSender sender, String[] args) {
        switch (args.length) {
            case 2 -> {
                return MegaClockAPI.getInstance().getClocks().stream()
                        .map(Clock::getName)
                        .toList();
            }
            case 3 -> {
                return ZoneId.getAvailableZoneIds()
                        .stream()
                        .filter(s -> s.startsWith(args[2]))
                        .toList();
            }
        }
        return null;
    }
}
