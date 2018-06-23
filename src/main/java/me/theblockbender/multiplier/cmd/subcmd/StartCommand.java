package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import org.bukkit.command.CommandSender;

public class StartCommand {
    private Main main;

    public StartCommand(Main main){
        this.main = main;
    }

    public void run(CommandSender commandSender, String[] args, String alias) {
        if(!commandSender.hasPermission("multiplier.admin.start")){
            main.getLanguage().sendMessage(commandSender, "no-permission");
            return;
        }
        // TODO validate arguments.
//        help-start: "{1}/{alias} {4}start <type> <multiplier> <duration> {2}Start a multiplier."
    }
}
