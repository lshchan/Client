package edu.neusoft.Client.Weather.Weather01.Weather;

import android.graphics.Bitmap;

public class lx_Weather {

	public static String city;
	public static String forecase_date;
	public static String current_date_time;
	public static String current_condition;
	public static String current_temp;
	public static String current_humidity;
	public static String current_image_url;
	public static Bitmap current_image;
	public static String current_wind;
	
	public static lx_Forecast[] day = new lx_Forecast[4];

	
	static {
		for (int i = 0; i< day.length; i++){
			day[i] = new lx_Forecast();
		}
	}
	
	public static String GetSmsMsg(){
		String msg = "";
		msg += city + ",";
		msg += current_condition + ", " + current_temp+". ";
		msg += day[0].day_of_week+", " + day[0].condition + ", " + 
				day[0].high + "/" + day[0].low; 
		return msg;
	}


}