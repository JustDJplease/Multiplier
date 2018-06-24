package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.util.UtilMain;
import org.bukkit.command.CommandSender;

public class EndCommand {
    private Main main;

    public EndCommand(Main main) {
        this.main = main;
    }

    public void run(CommandSender commandSender, String[] args, String alias) {
        // Validator:
        if (!commandSender.hasPermission("multiplier.admin.end")) {
            main.getLanguage().sendMessage(commandSender, "no-permission");
            return;
        }
        if (!UtilMain.isArgumentValidType(args[1])) {
            main.getLanguage().sendMessage(commandSender, "argument-invalid-type");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-end").replace("{alias}", alias));
            return;
        }
        // TODO run executor:
        // arg 1 = valid booster type.
    }
}
