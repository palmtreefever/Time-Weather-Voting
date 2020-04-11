package com.palmtreefever.TimeWeatherVoting.Utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.palmtreefever.TimeWeatherVoting.Main;

public class StartVote {
	
	public static void startweathervote() {
		String sName = Main.plugin.getConfig().getString("ServerPrefix");
		
		for (Player players : Bukkit.getOnlinePlayers()) {
			Arrays.weather_voters.add(players.getName()); //adds all online players to voters arraylist
			Main.weather_votingPeriod = true; //setting votingPeriod boolean to true
			//make the textcomponents for clickable texts
			TextComponent weatherVoteC = new TextComponent();
			TextComponent weatherVoteK = new TextComponent();
			weatherVoteC.setText((ChatColor.translateAlternateColorCodes('&', sName + "§e> Click here to vote to &bCLEAR &ethe weather!"))); //set clickable text
			weatherVoteC.setHoverEvent(new HoverEvent( Action.SHOW_TEXT, new ComponentBuilder( "§b Click to vote CLEAR!").create())); //display text msg when hovering
			weatherVoteC.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/weathervoteclear")); //runs command when they click the text

			weatherVoteK.setText((ChatColor.translateAlternateColorCodes('&', sName + "§e> Click here to vote to &bKEEP &ethe weather!"))); //set clickable text
			weatherVoteK.setHoverEvent(new HoverEvent( Action.SHOW_TEXT, new ComponentBuilder( "§b Click to vote KEEP!").create())); //display text msg when hovering
			weatherVoteK.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/weathervotekeep")); //runs command when they click the text
			//Sends online players the clickable texts
			players.spigot().sendMessage(weatherVoteC);
			players.spigot().sendMessage(weatherVoteK);
		}
		Weather_Timer.startTimer();
	}
	
	//time vote
	
	public static void starttimevote() {
		String sName = Main.plugin.getConfig().getString("ServerPrefix");
		
		for (Player players : Bukkit.getOnlinePlayers()) {
			Arrays.time_voters.add(players.getName()); //adds all online players to voters arraylist
			Main.time_votingPeriod = true; //setting votingPeriod boolean to true
			//make the textcomponents for clickable texts
			TextComponent timeVoteC = new TextComponent();
			TextComponent timeVoteK = new TextComponent();
			timeVoteC.setText((ChatColor.translateAlternateColorCodes('&', sName + "§e> Click here to vote to &bCHANGE &ethe time!"))); //set clickable text
			timeVoteC.setHoverEvent(new HoverEvent( Action.SHOW_TEXT, new ComponentBuilder( "§b Click to vote CHANGE!").create())); //display text msg when hovering
			timeVoteC.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/timevoteclear")); //runs command when they click the text

			timeVoteK.setText((ChatColor.translateAlternateColorCodes('&', sName + "§e> Click here to vote to &bKEEP &ethe time!"))); //set clickable text
			timeVoteK.setHoverEvent(new HoverEvent( Action.SHOW_TEXT, new ComponentBuilder( "§b Click to vote KEEP!").create())); //display text msg when hovering
			timeVoteK.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/timevotekeep")); //runs command when they click the text
			//Sends online players the clickable texts
			players.spigot().sendMessage(timeVoteC);
			players.spigot().sendMessage(timeVoteK);
		}
		Sleep_Timer.startTimer();
	}
}