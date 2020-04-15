package com.palmtreefever.TimeWeatherVoting.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.palmtreefever.TimeWeatherVoting.Main;
import com.palmtreefever.TimeWeatherVoting.Utils.Arrays;

public class Weather_Clear implements CommandExecutor {
	String sName = Main.plugin.getConfig().getString("ServerPrefix");
	String noPerm = Main.plugin.getConfig().getString("NoPermission");
	String noVote = Main.plugin.getConfig().getString("NoWeatherVote");
	String notApartOfCurrentVote = Main.plugin.getConfig().getString("NotApartOfCurrentVote");
	String noVoteChange = Main.plugin.getConfig().getString("NoVoteChange");
	String VotedForClear = Main.plugin.getConfig().getString("VotedForClear");
	String AlreadyVotedClear = Main.plugin.getConfig().getString("AlreadyVotedClear");


	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("weathervoteclear")) {
			if (sender.hasPermission("WeatherVote.Clear")) {
				if (Main.weather_votingPeriod == true) {
					if (Arrays.weather_voters.contains(sender.getName())) {
						if (!Arrays.weather_votersKeep.contains(sender.getName())) {
							if (!Arrays.weather_votersClear.contains(sender.getName())) {
								Arrays.weather_votersClear.add(sender.getName());
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + VotedForClear)));
							} else {
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + AlreadyVotedClear)));
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