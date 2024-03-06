package com.filkond.megaclock.commands.sub;

import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.utils.Translator;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateCommand implements ICommand {
    @Override // /clock create X Y Z NAME VERT/HORIZ BlockFace TIMEZONE
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
            y = Integer.parseInt(args[1]);
            z = Integer.parseInt(args[1]);
        } catch (NumberFormatException ignored) {
            player.sendMessage(Translator.of("number-format-error"));
            return;
        }

        try {
            BlockFace blockFace = args[5] == null ? player.getFacing() : BlockFace.valueOf(args[5].toUpperCase());
        } catch (IllegalArgumentException exception) {
            player.sendMessage();
            return;
        }


    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        return null;
    }
}
