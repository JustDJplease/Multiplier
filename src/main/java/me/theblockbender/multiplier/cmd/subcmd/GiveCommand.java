package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.util.UtilMain;
import org.bukkit.command.CommandSender;

public class GiveCommand {
    private Main main;

    public GiveCommand(Main main) {
        this.main = main;
    }

    /**
     * Method that is run upon executing /multiplier give ...
     *
     * @param commandSender Executor of the command. Can be the console.
     * @param args          Arguments of the command. There are 6 arguments.
     * @param alias         Possible alternative used instead of /give.
     */
    public void run(CommandSender commandSender, String[] args, String alias) {
        // Validator:
        if (!commandSender.hasPermission("multiplier.admin.give")) {
            main.getLanguage().sendMessage(commandSender, "no-permission");
            return;
        }
        if (!UtilMain.isArgumentOnlinePlayer(args[1])) {
            main.getLanguage().sendMessage(commandSender, "player-not-online");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
            return;
        }
        if (!UtilMain.isArgumentNumberInRange(args[2], 1, 100)) {
            main.getLanguage().sendMessage(commandSender, "argument-abstract-number");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
            return;
        }
        if (!UtilMain.isArgumentValidType(args[3])) {
            main.getLanguage().sendMessage(commandSender, "argument-invalid-type");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
            return;
        }
        if (!UtilMain.isArgumentNumberInRange(args[4], 1, 1000)) {
            main.getLanguage().sendMessage(commandSender, "argument-abstract-number");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
            return;
        }
        if (!UtilMain.isArgumentNumberInRange(args[5], -1, 86400)) {
            main.getLanguage().sendMessage(commandSender, "argument-abstract-number");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
            return;
        }
        // TODO run executor:
        // arg 1 = valid online player
        // arg 2 = valid amount
        // arg 3 = valid booster type
        // arg 4 = valid multiplier
        // arg 5 = valid duration
    }
}
