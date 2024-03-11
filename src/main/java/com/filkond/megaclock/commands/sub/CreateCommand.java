package com.filkond.megaclock.commands.sub;

import com.filkond.megaclock.MegaClockAPI;
import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.FontUtils;
import com.filkond.megaclock.utils.Translator;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateCommand implements ICommand {
    @Override // /clock create X Y Z NAME TIMEZONE BG FRAME SIZE Font
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        if (args.length < 8) {
            player.sendMessage(Translator.of("not-enough-args"));
            return;
        }

        int x, y, z;
        try {
            x = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
            z = Integer.parseInt(args[3]);
        } catch (NumberFormatException ignored) {
            player.sendMessage(Translator.of("number-format-error"));
            return;
        }

        var location = new Location(player.getWorld(), x, y, z);
        ClockDirection direction = ClockDirection.getDirection(player.getFacing().getOppositeFace());

        String name = args[4];
        var api = MegaClockAPI.getInstance();
        if (api.getClock(name) != null) {
            player.sendMessage(Translator.of("already-exists"));
            return;
        }

        ZoneId zoneId;
        try {
            zoneId = ZoneId.of(args[5]);
        } catch (DateTimeException ignored) {
            player.sendMessage(Translator.of("unknown-timezone"));
            return;
        }

        float size = FontUtils.defaultFont.getSize();
        if (args.length > 8) {
            try {
                size = Integer.parseInt(args[8]);
            } catch (NumberFormatException ignored) {
                player.sendMessage(Translator.of("number-format-error"));
                return;
            }
        }

        Font font = FontUtils.defaultFont.deriveFont(size);
        if (args.length > 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 8; i < args.length; i++) {
                sb.append(args[i]);
                sb.append(" ");
            }
            var fontName = sb.toString().replaceAll(" $", "");
            var tempFont = FontUtils.getFont(fontName, (int) size);
            if (tempFont == null) {
                player.sendMessage(Translator.of("unknown-font"));
                return;
            }
            font = tempFont;
        }

        boolean bg = Boolean.parseBoolean(args[6]);
        boolean frame = Boolean.parseBoolean(args[6]);
        api.createClock(name, location, zoneId, direction, font, bg, frame);
        player.sendMessage(Translator.of("successfully-created"));
    }

    @Override
    @Nullable
    public List<String> complete(CommandSender sender, String[] args) {
        List<String> out = new ArrayList<>();
        if (!(sender instanceof Player player)) {
            return null;
        }
        switch (args.length) {
            case 2 -> {
                return List.of(String.valueOf(player.getLocation().getBlockX()));
            }
            case 3 -> {
                return List.of(String.valueOf(player.getLocation().getBlockY()));
            }
            case 4 -> {
                return List.of(String.valueOf(player.getLocation().getBlockZ()));
            }
            case 5 -> {
                return List.of("Название");
            }
            case 6 -> {
                return ZoneId.getAvailableZoneIds()
                        .stream()
                        .filter(s -> s.startsWith(args[5]))
                        .toList();
            }
            case 7, 8 -> {
                return List.of("true", "false");
            }
            case 9 -> {
                return List.of("10", "15", "20");
            }
            case 10 -> {
                return List.of("Arial", "Times New Roman");
            }
        }
        return null;
    }
}
