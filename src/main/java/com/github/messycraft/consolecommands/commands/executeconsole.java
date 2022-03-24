package com.github.messycraft.consolecommands.commands;

import com.github.messycraft.consolecommands.ConsoleCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.*;

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
                Plugin mainclass = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
                if (mainclass.getConfig().getBoolean("allow_lp_command")) {
                    if (getServer().dispatchCommand(getConsoleSender(), arg)) {
                        sender.sendMessage("§eSuccessful executed §6" + arg + "§eas console");
                    } else {
                        sender.sendMessage("§eCommand §6" + arg + "§enot found");
                    }
                }
                else {
                    Boolean allow = true;
                    int l = arg.length();
                    if (l >= 2 && arg.substring(0,2).equals("lp")) allow = false;
                    if (allow && l >= 9 && arg.substring(0,9).equals("luckperms")) allow = false;
                    if (allow && l >= 4 && arg.substring(0,4).equals("perm")) allow = false;
                    if (allow && l >= 5 && arg.substring(0,5).equals("perms")) allow = false;
                    if (allow && l >= 10 && arg.substring(0,10).equals("permission")) allow = false;
                    if (allow && l >= 11 && arg.substring(0,11).equals("permissions")) allow = false;
                    if (allow) {
                        if (getServer().dispatchCommand(getConsoleSender(), arg)) {
                            sender.sendMessage("§eSuccessful executed §6" + arg + "§eas console");
                        } else {
                            sender.sendMessage("§eCommand §6" + arg + "§enot found");
                        }
                    }
                    else {
                        sender.sendMessage("§cYou can't use luckperms-command as console!");
                    }
                }
            }
            else {
                sender.sendMessage("§cYou don't have permission");
            }
        }
        return false;
    }
}
