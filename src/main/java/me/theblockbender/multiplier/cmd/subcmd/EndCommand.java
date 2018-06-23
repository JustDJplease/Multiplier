package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import org.bukkit.command.CommandSender;

public class EndCommand {
    private Main main;

    public EndCommand(Main main) {
        this.main = main;
    }

    public void run(CommandSender commandSender, String[] args, String alias) {
        if (!commandSender.hasPermission("multiplier.admin.end")) {
            main.getLanguage().sendMessage(commandSender, "no-permission");
            return;
        }
        // TODO validate arguments.
//        help-end: "{1}/{alias} {4}end <type> {2}Ends active multipliers of that type."
    }
}
