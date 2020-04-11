package com.palmtreefever.TimeWeatherVoting.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.palmtreefever.TimeWeatherVoting.Main;

public abstract class Sleep_Timer extends BukkitRunnable {
	
	private static int time = 61;

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
					if(Arrays.time_votersNight.size() > Arrays.time_votersDay.size()) {
						for(String players: Arrays.time_voters) {
							Player p = Bukkit.getPlayer(players);
							p.sendMessage((ChatColor.translateAlternateColorCodes('&', sName + ChatColor.AQUA + " KEEPING night! " + "Votes to KEEP the time: " + ChatColor.WHITE + Arrays.time_votersNight.size() 
									+ ChatColor.AQUA + " : Votes to CHANGE the time: " + ChatColor.WHITE + Arrays.time_votersDay.size())));
						}
						Arrays.clearTimeArrays();
						Main.time_votingPeriod = false;
						time = 61;
						return;
					} else if(Arrays.time_votersDay.size() > Arrays.time_votersNight.size()) {
						for(String players: Arrays.time_voters) {
							Player p = Bukkit.getPlayer(players);
							p.sendMessage((ChatColor.translateAlternateColorCodes('&', sName + ChatColor.AQUA + " CHANGING the time to day! " + "Votes to KEEP the time: " + ChatColor.WHITE + Arrays.time_votersNight.size() 
									+ ChatColor.AQUA + " : Votes to CHANGE the time: " + ChatColor.WHITE + Arrays.time_votersDay.size())));
						}
						Bukkit.getWorlds().get(0).setTime(0); //sets to sun rise
						
						for(String online : Arrays.time_voters) {
							Player o = Bukkit.getPlayer(online);
							o.setStatistic(Statistic.TIME_SINCE_REST, 0);
						}
						Arrays.clearTimeArrays();
						Main.time_votingPeriod = false;
						time = 61;
						return;
					} else if(Arrays.time_votersNight.size() == Arrays.time_votersNight.size()) {
						for(String players: Arrays.time_voters) {
							Player p = Bukkit.getPlayer(players);
							p.sendMessage((ChatColor.translateAlternateColorCodes('&', sName + ChatColor.AQUA + " There was a draw! " + "Votes to KEEP the time: " + ChatColor.WHITE + Arrays.time_votersNight.size() 
									+ ChatColor.AQUA + " : Votes to CHANGE the time: " + ChatColor.WHITE + Arrays.time_votersDay.size())));
						}
						Arrays.clearTimeArrays();
						Main.time_votingPeriod = false;
						time = 61;
						return;
					}
				}
			}
		}.runTaskTimer(Main.plugin, 0, 20l);
	}
}