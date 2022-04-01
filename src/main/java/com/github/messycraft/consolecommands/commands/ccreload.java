package com.github.messycraft.consolecommands.commands;

import com.github.messycraft.consolecommands.ConsoleCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ccreload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Plugin mainclass = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
        mainclass.reloadConfig();
        commandSender.sendMessage("§6ConsoleCommands 配置重新加载完成。");
        return false;
    }
}
