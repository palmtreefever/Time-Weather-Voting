package com.palmtreefever.TimeWeatherVoting.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.palmtreefever.TimeWeatherVoting.Main;
import com.palmtreefever.TimeWeatherVoting.Utils.Arrays;

public class Weather_Clear implements CommandExecutor {
	String sName = Main.plugin.getConfig().getString("ServerPrefix");

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("weathervoteclear")) {
			if (sender.hasPermission("WeatherVote.Clear")) {
				if (Main.weather_votingPeriod == true) {
					if (Arrays.weather_voters.contains(sender.getName())) {
						if (!Arrays.weather_votersKeep.contains(sender.getName())) {
							if (!Arrays.weather_votersClear.contains(sender.getName())) {
								Arrays.weather_votersClear.add(sender.getName());
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.GREEN + "Successfully voted to clear the weather!")));
							} else {
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "You already voted to clear the weather!")));
							}
						} else {
							sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "You can not change your vote from keep to clear!")));
						}
					} else {
						sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "You are not apart of the current vote!")));
					}
					// return true;
				} else {
					sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "There is no weather vote currently going on!")));
				}
				return true;
			}
			sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + ChatColor.RED + "You do not have permission!")));
		}
		return false;
	}
}