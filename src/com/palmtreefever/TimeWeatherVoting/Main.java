package com.palmtreefever.TimeWeatherVoting;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.palmtreefever.TimeWeatherVoting.Commands.Time_Clear;
import com.palmtreefever.TimeWeatherVoting.Commands.Time_Keep;
import com.palmtreefever.TimeWeatherVoting.Commands.Weather_Clear;
import com.palmtreefever.TimeWeatherVoting.Commands.Weather_Keep;
import com.palmtreefever.TimeWeatherVoting.Events.BedEnter;
import com.palmtreefever.TimeWeatherVoting.Events.PlayerLeave;
import com.palmtreefever.TimeWeatherVoting.Events.WeatherChange;

public class Main extends JavaPlugin implements Listener {
	
	public static Boolean weather_votingPeriod = false;
	public static Boolean time_votingPeriod = false;
	public static Plugin plugin;
	
	public void onEnable() {
		plugin = this;
		Bukkit.getPluginManager().registerEvents(new WeatherChange(), this);
		Bukkit.getPluginManager().registerEvents(new BedEnter(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
		getCommand("weathervoteclear").setExecutor(new Weather_Clear());
		getCommand("weathervotekeep").setExecutor(new Weather_Keep());
		getCommand("timevoteclear").setExecutor(new Time_Clear());
		getCommand("timevotekeep").setExecutor(new Time_Keep());
		loadConfig();
	}

	public void onDisable() {
		saveDefaultConfig();
	}

	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
	}
}