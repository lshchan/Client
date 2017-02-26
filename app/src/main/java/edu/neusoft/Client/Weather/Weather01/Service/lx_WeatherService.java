package edu.neusoft.Client.Weather.Weather01.Service;

import java.io.IOException;
import java.util.ArrayList;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;
import edu.neusoft.Client.Weather.Weather01.DB.*;
import edu.neusoft.Client.Weather.Weather01.SMS.*;
import edu.neusoft.Client.Weather.Weather01.Weather.*;

public class lx_WeatherService extends Service{
	
	private lx_DBAdapter dbAdapter ;
	private Thread workThread;
	private static ArrayList<lx_SimpleSms> smsList = new ArrayList<lx_SimpleSms>();
	private static int timeCounter = 1;
	
	public static void RequerSMSService(lx_SimpleSms sms){
		if (lx_Config.ProvideSmsService.equals("true")){
			smsList.add(sms);
		}
	}
	private void SaveSmsData(lx_SimpleSms sms){
		if (lx_Config.SaveSmsInfo.equals("true")){
			dbAdapter.SaveOneSms(sms);
		}
	}
	
	@Override
	public void onCreate() {
	    super.onCreate();
	         
        dbAdapter = new lx_DBAdapter(this);
	    dbAdapter.open();   
	      
	    Toast.makeText(this, "启动天气服务", Toast.LENGTH_LONG).show();    
	    workThread = new Thread(null,backgroudWork,"WorkThread");
	    
	    
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
	      super.onStart(intent, startId);

	      if (!workThread.isAlive()){
	    	  workThread.start();
	      }
	}
	
	@Override
	public void onDestroy() {
	     super.onDestroy();
	     Toast.makeText(this, "天气服务启动停止", Toast.LENGTH_SHORT).show();     
	     workThread.interrupt();
	}
	 
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	private Runnable backgroudWork = new Runnable(){
		@Override
		public void run() {
			try {
		
				while(!Thread.interrupted()){				

					ProcessSmsList();

					GetGoogleWeatherData();
					
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	private void ProcessSmsList(){
		if (smsList.size()==0){
			return;
		}
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent mPi = PendingIntent.getBroadcast(this, 0, new Intent(), 0);
		while(smsList.size()>0){
			lx_SimpleSms sms = smsList.get(0);
			smsList.remove(0);
			smsManager.sendTextMessage(sms.Sender, null, lx_Weather.GetSmsMsg(), mPi, null);
			sms.ReturnResult = lx_Weather.GetSmsMsg();
			SaveSmsData(sms);
			
		}
	}

	private void GetGoogleWeatherData(){
		Log.i("TIMER",String.valueOf(timeCounter));
		if (timeCounter-- < 0){
			timeCounter = Integer.parseInt(lx_Config.RefreshSpeed);
			Log.i("TIMER","NOW");
			try {
				lx_WeatherAdapter.GetWeatherData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
