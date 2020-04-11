package com.palmtreefever.TimeWeatherVoting.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.palmtreefever.TimeWeatherVoting.Main;

public abstract class Weather_Timer extends BukkitRunnable {
	
	private static int time = 61;
	private static int delayedTime = 6;

	public static void startTimer() {
		String sName = Main.plugin.getConfig().getString("ServerPrefix");
		new BukkitRunnable() {
			@Override
			public void run() {
				if (time > 0) {
					time--;
					//TODO:some how inform players about the time left without spamming the chat
					//System.out.println(time);
				} else {
					this.cancel();
					if(Arrays.weather_votersKeep.size() > Arrays.weather_votersClear.size()) {
						for(String players: Arrays.weather_voters) {
							Player p = Bukkit.getPlayer(players);
							p.sendMessage((ChatColor.translateAlternateColorCodes('&', sName + ChatColor.AQUA + " KEEPING weather! " + "Votes to KEEP the weather: " + ChatColor.WHITE + Arrays.weather_votersKeep.size() 
									+ ChatColor.AQUA + " : Votes to CLEAR the weather: " + ChatColor.WHITE + Arrays.weather_votersClear.size())));
						}
						Arrays.clearWeatherArrays();
						Main.weather_votingPeriod = false;
						time = 61;
						return;
					} else if(Arrays.weather_votersClear.size() > Arrays.weather_votersKeep.size()) {
						for(String players: Arrays.weather_voters) {
							Player p = Bukkit.getPlayer(players);
							p.sendMessage((ChatColor.translateAlternateColorCodes('&', sName + ChatColor.AQUA + " CLEARING weather! " + "Votes to KEEP the weather: " + ChatColor.WHITE + Arrays.weather_votersKeep.size() 
									+ ChatColor.AQUA + " : Votes to CLEAR the weather: " + ChatColor.WHITE + Arrays.weather_votersClear.size())));
						}
						Bukkit.getWorlds().get(0).setStorm(false);
						Arrays.clearWeatherArrays();
						Main.weather_votingPeriod = false;
						time = 61;
						return;
					} else if(Arrays.weather_votersKeep.size() == Arrays.weather_votersClear.size()) {
						for(String players: Arrays.weather_voters) {
							Player p = Bukkit.getPlayer(players);
							p.sendMessage((ChatColor.translateAlternateColorCodes('&', sName + ChatColor.AQUA + " There was a draw! " + "Votes to KEEP the weather: " + ChatColor.WHITE + Arrays.weather_votersKeep.size() 
									+ ChatColor.AQUA + " : Votes to CLEAR the weather: " + ChatColor.WHITE + Arrays.weather_votersClear.size())));
						}
						Arrays.clearWeatherArrays();
						Main.weather_votingPeriod = false;
						time = 61;
						return;
					}
				}
			}
		}.runTaskTimer(Main.plugin, 0, 20l);
	}
	
	public static void startDelayTimer() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (delayedTime > 0) {
					delayedTime--;
					//System.out.println(delayedTime);
				} else {
					this.cancel();
					delayedTime = 6;
					if(Bukkit.getWorlds().get(0).hasStorm()) {
						StartVote.startweathervote();
						return;
					} else {
						return;
					}
				}
			}
		}.runTaskTimer(Main.plugin, 0, 20l);
	}
}