package me.theblockbender.multiplier.cmd;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.cmd.subcmd.EndCommand;
import me.theblockbender.multiplier.cmd.subcmd.GiveCommand;
import me.theblockbender.multiplier.cmd.subcmd.MenuCommand;
import me.theblockbender.multiplier.cmd.subcmd.StartCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand implements CommandExecutor {

    private Main main;
    private EndCommand endCommand;
    private GiveCommand giveCommand;
    private MenuCommand menuCommand;
    private StartCommand startCommand;

    public BaseCommand(Main main) {
        this.main = main;
        endCommand = new EndCommand(main);
        giveCommand = new GiveCommand(main);
        menuCommand = new MenuCommand(main);
        startCommand = new StartCommand(main);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                if (commandSender instanceof Player)
                    menuCommand.run((Player) commandSender);
                else
                    main.getLanguage().sendMessage(commandSender, "no-player");
                return true;
            case 2:
                if (args[0].equalsIgnoreCase("end")) {
                    endCommand.run(commandSender, args, label);
                    return true;
                }
                break;
            case 4:
                if (args[0].equalsIgnoreCase("start")) {
                    startCommand.run(commandSender, args, label);
                    return true;
                }
                break;
            case 6:
                if (args[0].equalsIgnoreCase("give")) {
                    giveCommand.run(commandSender, args, label);
                    return true;
                }
                break;
            default:
                break;
        }
        sendHelp(commandSender, label);
        return true;
    }

    private void sendHelp(CommandSender commandSender, String alias) {
        commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-header").replace("{alias}", alias));
        commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-menu").replace("{alias}", alias));
        commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-give").replace("{alias}", alias));
        commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-start").replace("{alias}", alias));
        commandSender.sendMessage(main.getLanguage().getFormattedMessage("help-end").replace("{alias}", alias));
    }
}
