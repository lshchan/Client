package edu.neusoft.Client.Weather.Weather01.SMS;

import java.text.SimpleDateFormat;

public class lx_SimpleSms {
	
	public String Sender;
	public String Body;
	public String ReceiveTime;
	public String ReturnResult;

	public lx_SimpleSms(){
		
	}
	public lx_SimpleSms(String sender, String body){
    	this.Sender = sender;
    	this.Body = body;
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " " + "hh:mm:ss");
    	this.ReceiveTime = tempDate.format(new java.util.Date());
    	this.ReturnResult = "";
	}
	
}