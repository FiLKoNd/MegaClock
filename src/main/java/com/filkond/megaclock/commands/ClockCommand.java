package com.filkond.megaclock.commands;

import com.filkond.megaclock.MegaClock;
import com.filkond.megaclock.utils.Translator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ClockCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        if (args.length < 1) {
            for (String msg : Translator.ofList("help-message")) {
                player.sendMessage(msg);
            }
            return;
        }

        var cmd = MegaClock.getInstance().getSubcommands().get(args[0]);
        if (cmd != null) {
            cmd.execute(sender, args);
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return MegaClock.getInstance().getSubcommands().keySet().stream().toList();
        }

        if (args.length > 1) {
            var cmd = MegaClock.getInstance().getSubcommands().get(args[0]);
            if (cmd != null) {
                return cmd.complete(sender, args);
            }
        }
        return null;
    }
}
