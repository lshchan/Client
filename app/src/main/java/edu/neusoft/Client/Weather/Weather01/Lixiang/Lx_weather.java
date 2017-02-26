package edu.neusoft.Client.Weather.Weather01.Lixiang;

import edu.neusoft.Client.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import edu.neusoft.Client.Weather.Weather01.DB.lx_DBAdapter;
import edu.neusoft.Client.Weather.Weather01.Weather.lx_Weather;
import edu.neusoft.Client.Weather.Weather01.Service.lx_WeatherService;

public class Lx_weather extends Activity{

	final static int MENU_START_SERVICE= Menu.FIRST ;
	final static int MENU_STOP_SERVICE = Menu.FIRST + 1;
	final static int MENU_REFRESH = Menu.FIRST + 2;
	final static int MENU_QUIT = Menu.FIRST +3;
	
	private lx_DBAdapter dbAdapter ;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.lx_weather);
	        
	        dbAdapter = new lx_DBAdapter(this);
	        dbAdapter.open();  
			dbAdapter.LoadConfig();		
			
	 }
	 	 
	  @Override
	 public boolean onCreateOptionsMenu(Menu menu){

		 menu.add(0,MENU_START_SERVICE,0,"启动服务");
		 menu.add(0,MENU_STOP_SERVICE,1,"停止服务");
		 menu.add(0,MENU_REFRESH ,2,"刷新");
 		 menu.add(0,MENU_QUIT,3,"退出");
 		 return true;
	 }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item){
		 final Intent serviceIntent = new Intent(this, lx_WeatherService.class);
		 switch(item.getItemId()){
		 	case MENU_REFRESH:
		 		RefreshWeatherData();
	    		return true;	   
		 	case MENU_START_SERVICE:
		 		startService(serviceIntent);
	    		return true;	
		 	case MENU_STOP_SERVICE:
		 		stopService(serviceIntent);
	    		return true;	
	    	case MENU_QUIT:
	    		finish();
	    		break;
	    	}	
	    	return false;
	  } 

	 private void RefreshWeatherData(){
		 
		 // 当前温度
			TextView currentCondition = (TextView)findViewById(R.id.tab_weather_current_condition);
			TextView currentWind = (TextView)findViewById(R.id.tab_weather_current_wind);
			ImageView currentImage = (ImageView)findViewById(R.id.tab_weather_current_image);
			TextView currentCity = (TextView)findViewById(R.id.tab_weather_current_city);
			
			String msgCondition = "";
			msgCondition += "Temperature：" + lx_Weather.current_temp + ", ";
			msgCondition += lx_Weather.current_humidity ;
			currentCondition.setText(msgCondition);
			
			currentWind.setText(lx_Weather.current_wind + ", " + lx_Weather.current_date_time);	
			currentImage.setImageBitmap(lx_Weather.current_image);
			currentCity.setText(lx_Weather.city);
			
			 // 预报：第1天
			TextView forcastD1Date = (TextView)findViewById(R.id.tab_weather_d1_date);
			ImageView forcastD1Image = (ImageView)findViewById(R.id.tab_weather_d1_image);
			TextView forcastD1Temperature = (TextView)findViewById(R.id.tab_weather_d1_temperature);
			
			forcastD1Date.setText(lx_Weather.day[0].day_of_week);
			forcastD1Image.setImageBitmap(lx_Weather.day[0].image);
			
			String msgD1Temperature = lx_Weather.day[0].high + "/" + lx_Weather.day[0].low;
			forcastD1Temperature.setText(msgD1Temperature);
			
			 // 预报：第2天
			TextView forcastD2Date = (TextView)findViewById(R.id.tab_weather_d2_date);
			ImageView forcastD2Image = (ImageView)findViewById(R.id.tab_weather_d2_image);
			TextView forcastD2Temperature = (TextView)findViewById(R.id.tab_weather_d2_temperature);
			
			forcastD2Date.setText(lx_Weather.day[1].day_of_week);
			forcastD2Image.setImageBitmap(lx_Weather.day[1].image);
			
			String msgD2Temperature = lx_Weather.day[1].high + "/" + lx_Weather.day[1].low;
			forcastD2Temperature.setText(msgD2Temperature);
			
			 // 预报：第3天
			TextView forcastD3Date = (TextView)findViewById(R.id.tab_weather_d3_date);
			ImageView forcastD3Image = (ImageView)findViewById(R.id.tab_weather_d3_image);
			TextView forcastD3Temperature = (TextView)findViewById(R.id.tab_weather_d3_temperature);
			
			forcastD3Date.setText(lx_Weather.day[2].day_of_week);
			forcastD3Image.setImageBitmap(lx_Weather.day[2].image);
			
			String msgD3Temperature = lx_Weather.day[2].high + "/" + lx_Weather.day[2].low;
			forcastD3Temperature.setText(msgD3Temperature);
			
			
			 // 预报：第4天
			TextView forcastD4Date = (TextView)findViewById(R.id.tab_weather_d4_date);
			ImageView forcastD4Image = (ImageView)findViewById(R.id.tab_weather_d4_image);
			TextView forcastD4Temperature = (TextView)findViewById(R.id.tab_weather_d4_temperature);
			
			forcastD4Date.setText(lx_Weather.day[3].day_of_week);
			forcastD4Image.setImageBitmap(lx_Weather.day[3].image);
			
			String msgD4Temperature = lx_Weather.day[3].high + "/" + lx_Weather.day[3].low;
			forcastD4Temperature.setText(msgD4Temperature);
			
	 }
	 
}