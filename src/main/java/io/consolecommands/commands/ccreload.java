package io.consolecommands.commands;

import io.consolecommands.ConsoleCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ccreload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Plugin mainclass = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
        mainclass.reloadConfig();
        commandSender.sendMessage("§e§l[ConsoleCommand] §6Plugin has been reloaded");
        return false;
    }
}
