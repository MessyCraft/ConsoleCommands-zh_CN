package com.github.messycraft.consolecommands.commands;

import com.github.messycraft.consolecommands.ConsoleCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;

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

        // Anti-crash
        List<String> noloop = new ArrayList<>();
        for (int i=0;i<9;i++) {
            noloop.add("loopcmd " + i);
        }
        if (containsIgnoreCase(arg, noloop)) {
            sender.sendMessage("&c不要使用 /loopcmd 循环执行 /loopcmd! ");
            return false;
        }

        // Loop commands

        if (args.length < 2) {
            sender.sendMessage("§e用法: §6/loopcmd <循环次数> <命令>");
        }
        else {
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            if (pattern.matcher(args[0]).matches() && !args[0].equals("+") && !args[0].equals("-")) {
                int looptimes = Integer.parseInt(args[0]);
                if (looptimes > 0) {
                    if (sender.hasPermission("consolecommands.loopcmd")) {
                        Plugin mainclass = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
                        if (looptimes <= mainclass.getConfig().getInt("times_limit") || mainclass.getConfig().getInt("times_limit") == -1) {
                            if (getServer().dispatchCommand(sender, arg)) {
                                for (int i = 1; i < looptimes; i++) {
                                    getServer().dispatchCommand(sender, arg);
                                }
                                sender.sendMessage("§e已循环执行 §6" + arg + "§e§b" + looptimes + " §e次");
                            } else {
                                sender.sendMessage("§e未找到命令 §6" + arg);
                            }
                        }
                        else {
                            sender.sendMessage("§4[错误] §eloopcmd 执行次数限制为 §6" + mainclass.getConfig().getInt("times_limit")
                                    + "§e次。 §c您不能执行这个命令 §6" + looptimes + " §c次。");
                        }
                    } else {
                        sender.sendMessage("§c您没有权限");
                    }
                }
                else {
                    sender.sendMessage("§e用法: §6/loopcmd <次数> <命令>");
                    sender.sendMessage("§c<次数> 必须是一个整数! ");
                }
            }
            else {
                sender.sendMessage("§e用法: §6/loopcmd <次数> <命令>");
                sender.sendMessage("§c<次数> 必须是一个整数! ");
            }
        }
        return false;
    }

    // Tab complete

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");
            list.add("6");
            list.add("7");
            list.add("8");
            list.add("9");
            return list;
        }
        return null;
    }

    public boolean containsIgnoreCase(String s, List<String> list) {
        boolean c = false;
        s = s.toLowerCase();
        for (int i=0;i<list.size();i++) {
            if (s.contains(list.get(i))) {
                c = true;
                break;
            }
        }
        return c;
    }
}
