package com.filkond.megaclock.commands.sub;

import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.utils.Translator;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TimezoneCommand implements ICommand {
    @Override // /clock timezone <Название> <TIMEZONE>
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }


    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        return null;
    }
}
