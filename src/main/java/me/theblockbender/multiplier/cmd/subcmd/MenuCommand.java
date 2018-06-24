package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import org.bukkit.entity.Player;

public class MenuCommand {
    private Main main;

    public MenuCommand(Main main) {
        this.main = main;
    }

    public void run(Player player) {
        if (!player.hasPermission("multiplier.menu")) {
            main.getLanguage().sendMessage(player, "no-permission");
            return;
        }
        // TODO run executor:
    }
}
