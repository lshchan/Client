package edu.neusoft.Client.Weather.Weather01.Lixiang;

import edu.neusoft.Client.*;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import edu.neusoft.Client.Weather.Weather01.DB.lx_DBAdapter;
import edu.neusoft.Client.Weather.Weather01.SMS.*;

public class Lx_history extends ListActivity{
	final static int MENU_REFRESH = Menu.FIRST;
	final static int MENU_DELETE = Menu.FIRST+1;
	final static int MENU_QUIT = Menu.FIRST+2;
	
	private lx_DBAdapter dbAdapter ;
	private lx_SmsAdapter dataAdapter;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.lx_history);
	        
	        dbAdapter = new lx_DBAdapter(this);
		    dbAdapter.open();   
		    
		    dataAdapter = new lx_SmsAdapter(this);
	        setListAdapter(dataAdapter);
	 }
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu){
		 menu.add(0,MENU_REFRESH ,0,"刷新");
		 menu.add(0,MENU_DELETE,1,"清空数据");
		 menu.add(0,MENU_QUIT,1,"退出");
		 return true;
	 }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item){
		 switch(item.getItemId()){
		 	case MENU_REFRESH:
		 		lx_SmsAdapter.RefreshData();
		 		setListAdapter(dataAdapter);
	    		return true;	    	 	
	    	case MENU_DELETE:
	    		dbAdapter.DeleteAllSms();
	    		return true;  
	    	case MENU_QUIT:
	    		finish();
	    		break;
	    	}	
	    	return false;
	  } 
}