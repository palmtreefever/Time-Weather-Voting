package com.palmtreefever.TimeWeatherVoting.Utils;

import java.util.ArrayList;

public class Arrays {

	public static ArrayList<String> weather_voters = new ArrayList<String>();
	public static ArrayList<String> weather_votersClear = new ArrayList<String>();
	public static ArrayList<String> weather_votersKeep = new ArrayList<String>();
	
	public static ArrayList<String> time_voters = new ArrayList<String>();
	public static ArrayList<String> time_votersDay = new ArrayList<String>();
	public static ArrayList<String> time_votersNight = new ArrayList<String>();
	
	public static void clearWeatherArrays() {
		weather_voters.clear();
		weather_votersClear.clear();
		weather_votersKeep.clear();
	}
	public static void clearTimeArrays() {
		time_voters.clear();
		time_votersDay.clear();
		time_votersNight.clear();
	}
}