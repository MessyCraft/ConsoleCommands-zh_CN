package com.github.messycraft.consolecommands.commands;

import com.github.messycraft.consolecommands.ConsoleCommands;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.*;

public class executeplayer implements CommandExecutor, TabExecutor {
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
                    Plugin mainclass = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
                    if (arg.charAt(0) == 'c' && arg.charAt(1) == ':') {
                        Player player = getPlayer(args[0]);
                        player.chat(arg.substring(2,arg.length()));
                        sender.sendMessage("§eForced §3" + args[0] + " §esay");
                    }
                    else {
                        if (mainclass.getConfig().getBoolean("allow_lp_command")) {
                            if (getServer().dispatchCommand(getPlayer(args[0]), arg)) {
                                sender.sendMessage("§eForced §3" + args[0] + " §eexecute command §6" + arg);
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
                                if (getServer().dispatchCommand(getPlayer(args[0]), arg)) {
                                    sender.sendMessage("§eForced §3" + args[0] + " §eexecute command §6" + arg);
                                } else {
                                    sender.sendMessage("§eCommand §6" + arg + "§enot found");
                                }
                            }
                            else {
                                sender.sendMessage("§cYou can't use luckperms-command as other player!");
                            }
                        }
                    }
                }
            }
            else {
                sender.sendMessage("§cYou don't have permission");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 2) {
            List<String> list = new ArrayList<>();
            list.add("c:");
            return list;
        }
        return null;
    }
}