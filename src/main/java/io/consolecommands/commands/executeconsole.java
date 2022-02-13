package io.consolecommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getServer;

public class executeconsole implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String arg = new String();
        for (String s : args) {
            arg = arg + s + " ";
        }
        if (args.length == 0) {
            sender.sendMessage("§eUsage: §6/executeconsole <commands>");
        }
        else {
            if (sender.hasPermission("consolecommands.execute.console")) {
                if(getServer().dispatchCommand(getConsoleSender(),arg)) {
                    sender.sendMessage("§eSuccessful executed §6" + arg + " &e as console");
                }
                else {
                    sender.sendMessage("§eCommand§6 " + arg + " &e not found");
                }
            }
            else {
                sender.sendMessage("§cYou don't have permission");
            }
        }
        return false;
    }
}
