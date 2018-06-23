package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import org.bukkit.entity.Player;

public class MenuCommand {
    private Main main;

    public MenuCommand(Main main){
        this.main = main;
    }

    public void run(Player commandSender) {
        if(!commandSender.hasPermission("multiplier.menu")){
            main.getLanguage().sendMessage(commandSender, "no-permission");
            return;
        }
        // TODO validate arguments.
//        help-menu: "{1}/{alias} {2}Open multiplier menu."
//        help-give: "{1}/{alias} {4}give <player> <amount> <type> <multiplier> <duration> {2}Give a multiplier."
//        help-start: "{1}/{alias} {4}start <type> <multiplier> <duration> {2}Start a multiplier."
//        help-end: "{1}/{alias} {4}end <type> {2}Ends active multipliers of that type."
    }
}
