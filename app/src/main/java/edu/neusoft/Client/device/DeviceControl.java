package edu.neusoft.Client.device;

import java.util.ArrayList;
import java.util.List;

import edu.neusoft.Client.R;
import edu.neusoft.Client.common.ClientStart;
import edu.neusoft.Client.device.DeviceControl;
import edu.neusoft.Client.device.Lights;
import edu.neusoft.Client.device.DeviceControl.listener;
import edu.neusoft.Client.temperature.Temperature;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DeviceControl extends Activity {
	public int n;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.devicecontrol);
        
/*        Button buttonSearch = (Button)findViewById(R.id.SearchState);
        Button buttonUpdate = (Button)findViewById(R.id.UpdateState);
        Button buttonBack = (Button)findViewById(R.id.back);
        
        final TextView textView = (TextView)findViewById(R.id.StateOutput);
        n=(int)Math.random()*2;

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if (n==1)
            	{
            		textView.setText("灯是亮着的");
            	}
            	
            	if (n==0)
            	{
            		textView.setText("灯是关着的");
            	}
            }
          });
        
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	if (n==1)
            	{
            		n=0;
            	}
            	
            	if (n==0)
            	{
            		n=1;
            	}         	
            }
          });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	finish();
            }
          });*/
        ListView listView=(ListView) findViewById(R.id.ListView01);
        List<String>list=new ArrayList<String>();
        list.add("温度");
        list.add("电灯");
        list.add("电扇");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new listener());
        
        
        
	}
	
	class listener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent;
			switch(arg2){
			case 0:
    			intent= new Intent (DeviceControl.this,Temperature.class);
    			startActivity(intent);
				break;
			case 1:
				intent = new Intent(DeviceControl.this,Lights.class);
				startActivity(intent);
				break;
			}
			
		}
		
	}
	
	

}
