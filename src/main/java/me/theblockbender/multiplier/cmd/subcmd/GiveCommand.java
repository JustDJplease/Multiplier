package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCommand {
    private Main main;

    public GiveCommand(Main main){
        this.main = main;
    }

    public void run(CommandSender commandSender, String[] args, String alias) {
        if(!commandSender.hasPermission("multiplier.admin.give")){
            main.getLanguage().sendMessage(commandSender, "no-permission");
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if(target == null){
            main.getLanguage().sendMessage(commandSender, "player-not-online");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
            return;
        }
        int amount = 1;
        try {
            amount = Integer.parseInt(args[2]);
        }catch(NumberFormatException ex){
            main.getLanguage().sendMessage(commandSender, "argument-not-number");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
            return;
        }
        if(amount < 1){
            main.getLanguage().sendMessage(commandSender, "argument-negative-number");
            commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
            return;
        }
        // TODO validate arguments.
//        help-give: "{1}/{alias} {4}give <player> <amount> <type> <multiplier> <duration> {2}Give a multiplier."
    }
}
