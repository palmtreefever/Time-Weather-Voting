package com.palmtreefever.TimeWeatherVoting.Events;

import org.bukkit.World.Environment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import com.palmtreefever.TimeWeatherVoting.Main;
import com.palmtreefever.TimeWeatherVoting.Utils.StartVote;

public class BedEnter implements Listener {
	String bedSpawnSet = Main.plugin.getConfig().getString("BedSpawnSet");
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void BedEntered(PlayerBedEnterEvent e) {
		Environment world = e.getBed().getWorld().getEnvironment();
		if(!world.equals(Environment.NORMAL)) {// If player enters bed in any world besides the overworld
			return; 
		} else if(e.getBedEnterResult().equals(e.getBedEnterResult().NOT_POSSIBLE_NOW)){ // If players enter bed in overworld but cant sleep
			e.getPlayer().setBedSpawnLocation(e.getBed().getLocation());
			e.getPlayer().sendMessage(bedSpawnSet + " " + e.getBed().getLocation().getX() + " " + e.getBed().getLocation().getY() + " "+ e.getBed().getLocation().getZ());
			return;
		} else if (e.getBedEnterResult().equals(e.getBedEnterResult().OK)) { // If in overworld and can sleep
			if(Main.time_votingPeriod == true) { //if vote going on
				e.getPlayer().setBedSpawnLocation(e.getBed().getLocation());
				e.getPlayer().sendMessage(bedSpawnSet + " "+ e.getBed().getLocation().getX() + " " + e.getBed().getLocation().getY() + " "+ e.getBed().getLocation().getZ());
				e.setCancelled(true);
				return;
			} else { //if vote not started yet
			StartVote.starttimevote();
			e.getPlayer().setBedSpawnLocation(e.getBed().getLocation());
			e.getPlayer().sendMessage(bedSpawnSet + " " + e.getBed().getLocation().getX() + " " + e.getBed().getLocation().getY() + " "+ e.getBed().getLocation().getZ());
			e.setCancelled(true);
			return;
			}
		}
	}
}