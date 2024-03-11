package com.filkond.megaclock.commands.sub;

import com.filkond.megaclock.MegaClockAPI;
import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.utils.ClockDirection;
import com.filkond.megaclock.utils.FontUtils;
import com.filkond.megaclock.utils.Translator;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CreateCommand implements ICommand {
    @Override // /clock create X Y Z NAME TIMEZONE BG FRAME ClockFace Font
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
        if (args.length == 9) {
            try {
                direction = ClockDirection.valueOf(args[8].toUpperCase());
            } catch (IllegalArgumentException exception) {
                player.sendMessage(Translator.of("block-face-error"));
                return;
            }
        }

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

        Font font = FontUtils.defaultFont;
        if (args.length > 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 8; i < args.length; i++) {
                sb.append(args[i]);
                sb.append(" ");
            }
            font = Font.getFont(sb.toString().replaceAll(" $", ""));
            if (font == null) {
                player.sendMessage(Translator.of("unknown-font"));
                return;
            }
        }

        boolean bg = Boolean.parseBoolean(args[6]);
        boolean frame = Boolean.parseBoolean(args[6]);
        api.createClock(name, location, zoneId, direction, font, bg, frame);
        player.sendMessage(Translator.of("successfully-created"));
    }

    @Override
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
                        .collect(Collectors.toList());
            }
            case 7, 8 -> {
                return List.of("true", "false");
            }
            case 9 -> {
                return Arrays.stream(ClockDirection.values())
                        .map(Enum::name)
                        .filter(s -> s.startsWith(args[8]))
                        .toList();
            }
            case 10 -> {
                return List.of("Arial", "Times New Roman");
            }
        }
        return null;
    }
}
