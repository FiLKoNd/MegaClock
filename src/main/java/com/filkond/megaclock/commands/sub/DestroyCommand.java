package com.filkond.megaclock.commands.sub;

import com.filkond.megaclock.MegaClockAPI;
import com.filkond.megaclock.clock.Clock;
import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.utils.Translator;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DestroyCommand implements ICommand {
    @Override // /clock destroy <Название>
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Translator.of("not-enough-args"));
            return;
        }

        Clock clock = MegaClockAPI.getInstance().getClock(args[1]);
        if (clock == null) {
            player.sendMessage(Translator.of("unknown-clock"));
            return;
        }

        clock.destroy();
        MegaClockAPI.getInstance().getClocks().remove(clock);
        player.sendMessage(Translator.of("successful-destroyed"));
    }

    @Override
    @Nullable
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 2) {
            return MegaClockAPI.getInstance().getClocks().stream()
                    .map(Clock::getName)
                    .toList();
        }
        return null;
    }
}
