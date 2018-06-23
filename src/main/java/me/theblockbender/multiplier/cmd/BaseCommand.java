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

    private Main main = new Main();
    private EndCommand endCommand = new EndCommand();
    private GiveCommand giveCommand = new GiveCommand();
    private MenuCommand menuCommand = new MenuCommand();
    private StartCommand startCommand = new StartCommand();

    public BaseCommand(Main main) {
        this.main = main;
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
            case 1:
                sendHelp(commandSender);
                return true;
            default:
                sendHelp(commandSender);
                return true;
        }
        // /booster give <player> <amount> <type> <multiplier> <duration>
        // /booster start <type> <multiplier> <duration>
        // /booster end <type>
        // /booster
    }

    private void sendHelp(CommandSender commandSender) {
        main.getLanguage().sendMessage(commandSender, "help-header");
        main.getLanguage().sendMessage(commandSender, "help-menu");
        main.getLanguage().sendMessage(commandSender, "help-give");
        main.getLanguage().sendMessage(commandSender, "help-start");
        main.getLanguage().sendMessage(commandSender, "help-end");
    }
}
