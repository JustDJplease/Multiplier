package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.booster.BoosterType;
import me.theblockbender.multiplier.util.UtilBooster;
import me.theblockbender.multiplier.util.UtilMain;
import org.bukkit.command.CommandSender;

public class StartCommand {
    private Main main;

    public StartCommand(Main main) {
        this.main = main;
    }

    /**
     * Method that is run upon executing /multiplier start ...
     *
     * @param commandSender Executor of the command. Can be the console.
     * @param args          Arguments of the command. There are 4 arguments.
     * @param alias         Possible alternative used instead of /multiply.
     */
    public void run(CommandSender commandSender, String[] args, String alias) {
        // Validator:
        if (!commandSender.hasPermission("multiplier.admin.start")) {
            main.getLanguage().sendMessage(commandSender, "no-permission");
            return;
        }
        if (!UtilMain.isArgumentValidType(args[1])) {
            main.getLanguage().sendMessage(commandSender, "argument-invalid-type");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-start").replace("{alias}", alias));
            return;
        }
        if (!UtilMain.isArgumentNumberInRange(args[2], 1, 1000)) {
            main.getLanguage().sendMessage(commandSender, "argument-abstract-number");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-start").replace("{alias}", alias));
            return;
        }
        if (!UtilMain.isArgumentNumberInRange(args[3], -1, 86400)) {
            main.getLanguage().sendMessage(commandSender, "argument-abstract-number");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-start").replace("{alias}", alias));
            return;
        }
        // TODO run executor:
        // arg 1 = valid booster type
        // arg 2 = valid multiplier
        // arg 3 = valid duration
        UtilBooster.startBooster(BoosterType.valueOf(args[1].toLowerCase()), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }
}
