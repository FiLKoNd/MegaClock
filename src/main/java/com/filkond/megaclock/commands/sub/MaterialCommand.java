package com.filkond.megaclock.commands.sub;

import com.filkond.megaclock.MegaClock;
import com.filkond.megaclock.MegaClockAPI;
import com.filkond.megaclock.clock.Clock;
import com.filkond.megaclock.commands.ICommand;
import com.filkond.megaclock.utils.Translator;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MaterialCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) { // /clock material CLOCK FIELD add/set MATERIALS
        if (!(sender instanceof Player player)) {
            return;
        }

        if (args.length < 5) {
            player.sendMessage(Translator.of("not-enough-args"));
            return;
        }

        Clock clock = MegaClockAPI.getInstance().getClock(args[1]);
        if (clock == null) {
            player.sendMessage(Translator.of("unknown-clock"));
            return;
        }

        MaterialField field;
        try {
            field = MaterialField.valueOf(args[2].toUpperCase());
        } catch (IllegalArgumentException ignored) {
            Translator.ofList("help-message").forEach(player::sendMessage);
            return;
        }

        List<Material> materials = new ArrayList<>();
        for (String id : args[4].split(",")) {
            var material = Material.getMaterial(id);
            if (material != null)
                materials.add(material);
        }

        if (materials.isEmpty()) {
            Translator.ofList("help-message").forEach(player::sendMessage);
            return;
        }

        switch (args[3]) {
            case "add" -> add(clock, materials, field);
            case "set" -> set(clock, materials, field);
            default -> Translator.ofList("help-message").forEach(player::sendMessage);
        }
    }

    private void add(Clock clock, List<Material> materials, MaterialField field) {
        switch (field) { // хуйня ебанная
            case TEXT -> {
                var newMaterials = new ArrayList<>(clock.getBuilder().getCharMaterials());
                newMaterials.addAll(materials);
                clock.getBuilder().setCharMaterials(newMaterials);
                clock.update(true);
            }
            case BACKGROUND -> {
                var newMaterials = new ArrayList<>(clock.getBuilder().getBgMaterials());
                newMaterials.addAll(materials);
                clock.getBuilder().setBgMaterials(newMaterials);
                clock.getBuilder().setup(clock.getTimeText());
            }
        }
    }

    private void set(Clock clock, List<Material> materials, MaterialField field) {
        switch (field) { // и это тоже
            case TEXT -> {
                clock.getBuilder().setCharMaterials(materials);
                clock.update(true);
            }
            case BACKGROUND -> {
                clock.getBuilder().setBgMaterials(materials);
                clock.getBuilder().setup(clock.getTimeText());
            }
        }
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
                return Arrays.stream(MaterialField.values())
                        .map(Enum::name)
                        .toList();
            }

            case 4 -> {
                return List.of("add", "set");
            }

            case 5 -> {
                return Stream.of("STONE,GRASS_BLOCK,DIAMOND_BLOCK", "WHITE_WOOL,PINK_WOOL,LIGHT_BLUE_WOOL,BLUE_WOOL")
                        .filter(s -> s.startsWith(args[3]))
                        .toList();
            }
        }
        return null;
    }

    enum MaterialField {
        BACKGROUND,
        TEXT;
    }
}
