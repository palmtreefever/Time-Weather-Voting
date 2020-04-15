package com.palmtreefever.TimeWeatherVoting.Commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.palmtreefever.TimeWeatherVoting.Main;
import com.palmtreefever.TimeWeatherVoting.Utils.Arrays;

public class Weather_Keep implements CommandExecutor {
	String sName = Main.plugin.getConfig().getString("ServerPrefix");
	String noPerm = Main.plugin.getConfig().getString("NoPermission");
	String noVote = Main.plugin.getConfig().getString("NoWeatherVote");
	String notApartOfCurrentVote = Main.plugin.getConfig().getString("NotApartOfCurrentVote");
	String noVoteChange = Main.plugin.getConfig().getString("NoVoteChange");
	String VotedForKeep = Main.plugin.getConfig().getString("VotedForKeep");
	String AlreadyVotedKeep = Main.plugin.getConfig().getString("AlreadyVotedKeep");

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("weathervotekeep")) {
			if (sender.hasPermission("WeatherVote.Keep")) {
				if (Main.weather_votingPeriod == true) {
					if (Arrays.weather_voters.contains(sender.getName())) {
						if (!Arrays.weather_votersClear.contains(sender.getName())) {
							if (!Arrays.weather_votersKeep.contains(sender.getName())) {
								Arrays.weather_votersKeep.add(sender.getName());
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + VotedForKeep)));
							} else {
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + AlreadyVotedKeep)));
							}
						} else {
							sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + noVoteChange)));
						}
					} else {
						sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + notApartOfCurrentVote)));
					}
					// return true;
				} else {
					sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + noVote)));
				}
				return true;
			}
			sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + noPerm)));
		}
		return false;
	}
}