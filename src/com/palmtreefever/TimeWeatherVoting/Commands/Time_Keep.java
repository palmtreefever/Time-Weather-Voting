package com.palmtreefever.TimeWeatherVoting.Commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.palmtreefever.TimeWeatherVoting.Main;
import com.palmtreefever.TimeWeatherVoting.Utils.Arrays;

public class Time_Keep implements CommandExecutor {
	String sName = Main.plugin.getConfig().getString("ServerPrefix");
	String noPerm = Main.plugin.getConfig().getString("NoPermission");
	String noVote = Main.plugin.getConfig().getString("NoTimeVote");
	String noVoteChange = Main.plugin.getConfig().getString("NoVoteChange");
	String notApartOfCurrentVote = Main.plugin.getConfig().getString("NotApartOfCurrentVote");
	String VotedForNight = Main.plugin.getConfig().getString("VotedForNight");
	String AlreadyVotedNight = Main.plugin.getConfig().getString("AlreadyVotedNight");

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("timevotekeep")) {
			if (sender.hasPermission("TimeVote.Keep")) {
				if (Main.time_votingPeriod == true) {
					if (Arrays.time_voters.contains(sender.getName())) {
						if (!Arrays.time_votersDay.contains(sender.getName())) {
							if (!Arrays.time_votersNight.contains(sender.getName())) {
								Arrays.time_votersNight.add(sender.getName());
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + VotedForNight)));
							} else {
								sender.sendMessage((ChatColor.translateAlternateColorCodes('&',sName + AlreadyVotedNight)));
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