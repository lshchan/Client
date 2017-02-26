package edu.neusoft.Client.Database;


public class Config {
	public static String ServerTel; //服务器电话号码
	public static String ControlMethod; //与服务器通讯方式
	public static String CityName; //城市名
	public static String RefreshSpeed; //刷新速度
	public static String StartTime; //预案开启时间
	public static String ServerIP; //服务器IP地址
	public static String ServerCom; //服务器端口
	public static String WeatherService; //天气服务
	public static String LocationService; //定位服务
	
	public static void LoadDefaultConfig(){
		ServerTel = "+8613795120260";
		ControlMethod = "sms";
		CityName = "Dalian";
		RefreshSpeed = "60";
		WeatherService = "true";
		LocationService = "true";
		StartTime = "06:00";
		ServerIP = "127.0.0.1";
		ServerCom = "5431";
	}
	

}
