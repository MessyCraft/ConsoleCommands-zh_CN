package io.consolecommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class executeplayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String arg = new String();
        for (int i=1;i<args.length;i++) {
            arg = arg + args[i] + " ";
        }
        if (args.length < 2) {
            sender.sendMessage("§eUsage: §6/executeplayer <player> [c:<text>/<command>]");
        }
        else {
            if (sender.hasPermission("consolecommands.execute.player")) {
                if(getPlayer(args[0]) == null) {
                    sender.sendMessage("§ePlayer §3" + args[0] + " §enot found");
                }
                else {
                    if (getServer().dispatchCommand(getPlayer(args[0]), arg)) {
                        sender.sendMessage("§eForced §3" + args[0] + " §eexecute command §6" + arg);
                    } else {
                        sender.sendMessage("§eCommand §6" + arg + "§enot found");
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