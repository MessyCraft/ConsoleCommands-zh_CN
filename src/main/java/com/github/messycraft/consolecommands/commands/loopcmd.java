package com.github.messycraft.consolecommands.commands;

import com.github.messycraft.consolecommands.ConsoleCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.bukkit.Bukkit.getServer;

public class loopcmd implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String arg = new String();
        for (int i=1;i<args.length;i++) {
            arg = arg + args[i] + " ";
        }
        if (args.length < 2) {
            sender.sendMessage("§eUsage: §6/loopcmd <looptimes> <command>");
        }
        else {
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            if (pattern.matcher(args[0]).matches() && !args[0].equals("+") && !args[0].equals("-")) {
                int looptimes = Integer.parseInt(args[0]);
                if (looptimes > 0) {
                    if (sender.hasPermission("consolecommands.loopcmd")) {
                        Plugin mainclass = com.github.messycraft.consolecommands.ConsoleCommands.getProvidingPlugin(com.github.messycraft.consolecommands.ConsoleCommands.class);
                        if (looptimes <= mainclass.getConfig().getInt("times_limit") || mainclass.getConfig().getInt("times_limit") == -1) {
                            if (getServer().dispatchCommand(sender, arg)) {
                                for (int i = 1; i < looptimes; i++) {
                                    getServer().dispatchCommand(sender, arg);
                                }
                                sender.sendMessage("§eCommand §6" + arg + "§ecycles §b" + looptimes + " §etimes");
                            } else {
                                sender.sendMessage("§eCommand §6" + arg + "§enot found");
                            }
                        }
                        else {
                            sender.sendMessage("§4[ERROR] §eloopcmd times limit: §6" + mainclass.getConfig().getInt("times_limit")
                                    + "§e. §cYou can't cycle the command §6" + looptimes + " §ctimes.");
                        }
                    } else {
                        sender.sendMessage("§cYou don't have permission");
                    }
                }
                else {
                    sender.sendMessage("§eUsage: §6/loopcmd <looptimes> <command>");
                    sender.sendMessage("§c<looptimes> 需要填写一个正整数！");
                }
            }
            else {
                sender.sendMessage("§eUsage: §6/loopcmd <looptimes> <command>");
                sender.sendMessage("§c<looptimes> 需要填写一个正整数！");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("5");
            list.add("20");
            return list;
        }
        return null;
    }
}
