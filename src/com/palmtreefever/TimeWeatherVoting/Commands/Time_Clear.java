package com.palmtreefever.TimeWeatherVoting.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.palmtreefever.TimeWeatherVoting.Main;
import com.palmtreefever.TimeWeatherVoting.Utils.Arrays;

public class Time_Clear implements CommandExecutor {
	String sName = Main.plugin.getConfig().getString("ServerPrefix");

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("timevoteclear")) {
			if (sender.hasPermission("TimeVote.Clear")) {
				if (Main.time_votingPeriod == true) {
					if (Arrays.time_voters.contains(sender.getName())) {
						if (!Arrays.time_votersNight.contains(sender.getName())) {
							if (!Arrays.time_votersDay.contains(sender.getName())) {
								Arrays.time_votersDay.add(sender.getName());
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.GREEN + "Successfully voted to change the time to Day!")));
							} else {
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "You already voted to change the time!")));
							}
						} else {
							sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "You can not change your vote from night to day!")));
						}
					} else {
						sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "You are not apart of the current vote!")));
					}
					// return true;
				} else {
					sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "There is no time vote currently going on!")));
				}
				return true;
			}
			sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "You do not have permission!")));
		}
		return false;
	}
}