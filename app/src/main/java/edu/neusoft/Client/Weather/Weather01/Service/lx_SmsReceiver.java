package edu.neusoft.Client.Weather.Weather01.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;
import edu.neusoft.Client.Weather.Weather01.DB.lx_Config;
import edu.neusoft.Client.Weather.Weather01.SMS.lx_SimpleSms;


public class lx_SmsReceiver extends BroadcastReceiver{	

	private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	  
  @Override
  public void onReceive(Context context, Intent intent){    
    if (intent.getAction().equals(SMS_ACTION)){
      Bundle bundle = intent.getExtras();
      
      if (bundle != null){
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];
        for (int i = 0; i<objs.length; i++){ 
          messages[i] = 
          SmsMessage.createFromPdu((byte[]) objs[i]);
        }
      
        String smsBody = messages[0].getDisplayMessageBody();
        String smsSender =  messages[0].getDisplayOriginatingAddress();
        if (smsBody.trim().equals(lx_Config.KeyWord) && lx_Config.ProvideSmsService.equals("true")){
        	lx_SimpleSms simpleSms = new lx_SimpleSms(smsSender, smsBody);
        	lx_WeatherService.RequerSMSService(simpleSms);
        	Toast.makeText(context, "李响提示：接收到服务请求短信", Toast.LENGTH_SHORT).show();
        }
      }  
    }
  }
}