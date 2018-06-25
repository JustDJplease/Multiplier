package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.gui.PrepareGui;
import org.bukkit.entity.Player;

public class MenuCommand {
    private Main main;
    private PrepareGui prepareGui;

    public MenuCommand(Main main) {
        this.main = main;
        prepareGui = new PrepareGui(main);
    }

    /**
     * Method that is run upon executing /multiplier (with no other args)
     *
     * @param player Executor of the command. Cannot be the console.
     */
    public void run(Player player) {
        // Validator:
        if (!player.hasPermission("multiplier.menu")) {
            main.getLanguage().sendMessage(player, "no-permission");
            return;
        }
        // Executor:
        prepareGui.openGui(player);
    }
}
