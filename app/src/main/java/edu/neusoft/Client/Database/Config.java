package edu.neusoft.Client.Database;


public class Config {
	public static String ServerTel; //�������绰����
	public static String ControlMethod; //�������ͨѶ��ʽ
	public static String CityName; //������
	public static String RefreshSpeed; //ˢ���ٶ�
	public static String StartTime; //Ԥ������ʱ��
	public static String ServerIP; //������IP��ַ
	public static String ServerCom; //�������˿�
	public static String WeatherService; //��������
	public static String LocationService; //��λ����
	
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
