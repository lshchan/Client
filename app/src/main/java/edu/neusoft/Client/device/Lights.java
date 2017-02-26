package edu.neusoft.Client.device;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import edu.neusoft.Client.R;

public class Lights extends Activity implements OnClickListener{
	
	private static RadioButton radioButton01;
	private static RadioButton radioButton02;


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.back:
		finish();
		Intent intent = new Intent(Lights.this,DeviceControl.class );
		startActivity(intent);
		
		break;
		case R.id.reset:
		
		SendSMS("1333",newStatus());
		Toast.makeText(this,newStatus(),Toast.LENGTH_LONG).show();
		break;
		}
		
	}
	private String newStatus(){
		StringBuilder  ns = new StringBuilder();
		ns.append("10*keting*");
		if(radioButton01.isChecked())
			    ns.append("1");
		if(radioButton02.isChecked())
			    ns.append("0");
		return ns.toString();
			
		
	}
	private void SendSMS(String telnumer,String content){
		Intent intent = new Intent(
				"android.provider.Telephony.SMS_SEND");
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent  mpi = PendingIntent.getBroadcast(
				this,0,intent,PendingIntent.FLAG_ONE_SHOT);
		smsManager.sendTextMessage(telnumer,null,
				content,mpi,null);
		
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		
		
		Button backButton=(Button)findViewById(R.id.back);
		Button refreshButton=(Button)findViewById(R.id.refresh);
		Button getButton=(Button)findViewById(R.id.getstatus);
		Button setButton=(Button)findViewById(R.id.reset);
		radioButton01=(RadioButton)findViewById(R.id.radiobutton1_on);
		radioButton02=(RadioButton)findViewById(R.id.radiobutton1_off);
		backButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);
		getButton.setOnClickListener(this);
		setButton.setOnClickListener(this);
	}
	

}
