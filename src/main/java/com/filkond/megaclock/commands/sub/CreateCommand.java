package com.filkond.megaclock.commands.sub;

import com.filkond.megaclock.commands.ICommand;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateCommand implements ICommand {
    @Override // /clock create X Y Z NAME VERT/HORIZ BlockFace
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
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
