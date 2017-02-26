package edu.neusoft.Client.Weather.Weather01.Lixiang;

import edu.neusoft.Client.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import edu.neusoft.Client.Weather.Weather01.DB.lx_Config;
import edu.neusoft.Client.Weather.Weather01.DB.lx_DBAdapter;

public class Lx_setup extends Activity{
	final static int MENU_RESTORE = Menu.FIRST;
	final static int MENU_QUIT = Menu.FIRST+1;
	
	private EditText cityNameView;
	private EditText refreshSpeedView;
	private CheckBox smsServiceView;
	private CheckBox saveSmsInfoView;
	private EditText keyWorkView;
	
	public static lx_DBAdapter dbAdapter ;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.lx_setup);
	        
			cityNameView = (EditText)findViewById(R.id.tab_setup_city_name);
			refreshSpeedView = (EditText)findViewById(R.id.tab_setup_refresh_speed);
			smsServiceView = (CheckBox)findViewById(R.id.tab_setup_sms_service);
			saveSmsInfoView = (CheckBox)findViewById(R.id.tab_setup_save_sms_info);
			keyWorkView = (EditText)findViewById(R.id.tab_setup_key_work);			 

			dbAdapter = new lx_DBAdapter(this);
		    dbAdapter.open();    
		    UpdateUI();
		    
	        Button applyBtn = (Button)findViewById(R.id.tab_setup_apply);
	        applyBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					SaveConfig();			
				}
			});
	        Button cancelBtn = (Button)findViewById(R.id.tab_setup_cancel);
	        cancelBtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dbAdapter.LoadConfig();
					UpdateUI();
				}       	
	        }
	        );
	        
	       
	 }
	 
	   @Override
	 public boolean onCreateOptionsMenu(Menu menu){
		 menu.add(0,MENU_RESTORE ,0,"ª÷∏¥≥ı º…Ë÷√");
 		 menu.add(0,MENU_QUIT,1,"ÕÀ≥ˆ");
 		 return true;
	 }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item){
		 switch(item.getItemId()){
		 	case MENU_RESTORE:
		 		RestoreDefaultSetup();
	    		return true;	 
	    	case MENU_QUIT:
	    		finish();
	    		break;
	    	}	
	    	return false;
	  } 
	 
	 private void RestoreDefaultSetup(){
		 lx_Config.LoadDefaultConfig();
		 UpdateUI();
		 dbAdapter.SaveConfig();
	 }
	 
	 private void UpdateUI(){
		 cityNameView.setText(lx_Config.CityName);
		 refreshSpeedView.setText(lx_Config.RefreshSpeed);		 
		 smsServiceView.setChecked(lx_Config.ProvideSmsService.equals("true")?true:false);
		 saveSmsInfoView.setChecked(lx_Config.SaveSmsInfo.equals("true")?true:false);
		 keyWorkView.setText(lx_Config.KeyWord);
	 }
	 
	 private void SaveConfig(){
		lx_Config.CityName = cityNameView.getText().toString().trim();
		lx_Config.RefreshSpeed = refreshSpeedView.getText().toString();
		if (smsServiceView.isChecked())
		{
			lx_Config.ProvideSmsService = "true";
		}
		else
		{
			lx_Config.ProvideSmsService = "false";
		}
		if (saveSmsInfoView.isChecked())
		{
			lx_Config.SaveSmsInfo = "true";
		}
		else{
			lx_Config.SaveSmsInfo = "false";
		}
		
		lx_Config.KeyWord = keyWorkView.getText().toString().trim();

		dbAdapter.SaveConfig();	
	 }
}

