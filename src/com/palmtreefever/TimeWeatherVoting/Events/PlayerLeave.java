package com.palmtreefever.TimeWeatherVoting.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.palmtreefever.TimeWeatherVoting.Main;
import com.palmtreefever.TimeWeatherVoting.Utils.Arrays;

public class PlayerLeave implements Listener {

	String sName = Main.plugin.getConfig().getString("ServerPrefix");

	@EventHandler
	public void playerleft(PlayerQuitEvent event) {
		String quitter = event.getPlayer().getName();
		if(Arrays.weather_voters.contains(quitter)) {
			Arrays.weather_voters.remove(quitter);
		} 
		if(Arrays.time_voters.contains(quitter)) {
			Arrays.time_voters.remove(quitter);
		} 
		return;
	}
}