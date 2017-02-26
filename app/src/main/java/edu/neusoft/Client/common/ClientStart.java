package edu.neusoft.Client.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import edu.neusoft.Client.R;
import edu.neusoft.Client.SystemSet.SystemSet;
import edu.neusoft.Client.Weather.Weather01.Lixiang.Weather01;
import edu.neusoft.Client.device.DeviceControl;

public class ClientStart extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        GridView gridView=(GridView)findViewById(R.id.gridview);
        ArrayList<HashMap<String, Object>> lstImageItem=
        	new ArrayList<HashMap<String, Object>>();
        	
        HashMap<String, Object> map0=new HashMap<String,Object>();
        map0.put("Image", R.drawable.app_icon);
        map0.put("Text", "温度");
        lstImageItem.add(map0);
        
        HashMap<String, Object> map1=new HashMap<String,Object>();
        map1.put("Image", R.drawable.sln);
        map1.put("Text", "电器控制");
        lstImageItem.add(map1);
        
        HashMap<String, Object> map2=new HashMap<String,Object>();
        map2.put("Image", R.drawable.open);
        map2.put("Text", "预案管理");
        lstImageItem.add(map2);
        
        HashMap<String, Object> map3=new HashMap<String,Object>();
        map3.put("Image", R.drawable.close);
        map3.put("Text", "4");
        lstImageItem.add(map2);
        
        SimpleAdapter saImageItems=new SimpleAdapter(this,lstImageItem,R.layout.function,new String[]{"Image","Text"},new int[]{R.id.ItemImage,R.id.ItemText});
        
        gridView.setAdapter(saImageItems);
        
        gridView.setOnItemClickListener(new ItemClickListener());
        
        }
    class ItemClickListener implements OnItemClickListener{
    	public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3)
    	{
    		Intent intent;
    		switch (arg2)
    		{
    		case 0:
    			intent= new Intent (ClientStart.this,SystemSet.class);
    			startActivity(intent);
    			break;
    		case 1:
    			intent = new Intent (ClientStart.this,DeviceControl.class);
    			startActivity(intent);
    			break;
    		case 2:
    			intent = new Intent (ClientStart.this,Weather01.class);
    			startActivity(intent);
    			break;
    		case 3:
    			break;
    			default:
    				break;
    		}
    	}
    	
    	
    	}
    }