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
            sender.sendMessage("§e用法: §6/executeplayer <玩家> c:<文字>/<命令>");
        }
        else {
            if (sender.hasPermission("consolecommands.execute.player")) {
                if(getPlayer(args[0]) == null) {
                    sender.sendMessage("§e未找到玩家 §3" + args[0]);
                }
                else {
                    Plugin mainclass = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
                    if (arg.startsWith("c:")) {
                        Player player = getPlayer(args[0]);
                        player.chat(arg.substring(2));
                        sender.sendMessage("§e已强制 §3" + args[0] + " §e发送消息");
                    }
                    else {
                        if (mainclass.getConfig().getBoolean("allow_lp_command")) {
                            cmdBannedP(arg,sender,getPlayer(args[0]));
                        }
                        else {
                            Boolean allow = true;
                            if (arg.startsWith("lp")) allow = false;
                            if (allow && arg.startsWith("luckperms")) allow = false;
                            if (allow && arg.startsWith("perm")) allow = false;
                            if (allow && arg.startsWith("perms")) allow = false;
                            if (allow && arg.startsWith("permission")) allow = false;
                            if (allow && arg.startsWith("permissions")) allow = false;
                            if (allow) {
                                cmdBannedP(arg,sender,getPlayer(args[0]));
                            }
                            else {
                                sender.sendMessage("§c您不能以玩家的身份执行 LuckPerms 的命令! ");
                            }
                        }
                    }
                }
            }
            else {
                sender.sendMessage("§c您没有权限");
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

    public void cmdBannedP(String cmd, CommandSender sender, Player to) {
        Plugin m = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
        List<String> list = m.getConfig().getStringList("bannedcmd_asplayer");
        boolean banned = false;
        for (int i=0;i<list.size();i++) {
            if (cmd.toLowerCase().startsWith(list.get(i).toLowerCase() + " ")) {
                banned = true;
                break;
            }
        }
        if (!banned) {
            if (getServer().dispatchCommand(to, cmd)) {
                sender.sendMessage("§e成功以 §3" + to.getName() + " §e的身份执行命令 §6" + cmd);
            } else {
                sender.sendMessage("§e未找到命令 §6" + cmd);
            }
        }
        else {
            sender.sendMessage("§c您不能以玩家的身份执行这个命令! ");
        }
    }
}