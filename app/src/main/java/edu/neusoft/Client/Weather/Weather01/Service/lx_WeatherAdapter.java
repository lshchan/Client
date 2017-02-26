package edu.neusoft.Client.Weather.Weather01.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import edu.neusoft.Client.Weather.Weather01.DB.lx_Config;
import edu.neusoft.Client.Weather.Weather01.Weather.lx_Weather;

public class lx_WeatherAdapter {
	
	public static void GetWeatherData() throws IOException, Throwable {

    	String queryString = "http://www.google.com/ig/api?weather=" + lx_Config.CityName;

    	URL aURL = new URL(queryString.replace(" ", "%20"));
     	URLConnection conn = aURL.openConnection();
        conn.connect();
        InputStream is = conn.getInputStream();
         
      	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(is,"UTF-8");
        
        int dayCounter = 0;
        while(parser.next()!= XmlPullParser.END_DOCUMENT){
  
        	String element = parser.getName(); 
        	if (element != null && element.equals("forecast_information")){
        		while(true){
        			int eventCode = parser.next();
        			element =  parser.getName();
        			if (eventCode == XmlPullParser.START_TAG){
        				if (element.equals("city")){
    	            		lx_Weather.city =  parser.getAttributeValue(0);
        				}else if (element.equals("current_date_time")){ 
        					lx_Weather.current_date_time = parser.getAttributeValue(0);  
        				}
        			}
        			
        			if (element.equals("forecast_information") && 
        					eventCode == XmlPullParser.END_TAG){
        				break;
        			}
        		}    		
        	}
        	if (element != null && element.equals("current_conditions")){
        		while(true){
        			int eventCode = parser.next();
        			element =  parser.getName();
        			if (eventCode == XmlPullParser.START_TAG){
        				if (element.equals("condition")){
    	            		lx_Weather.current_condition =  parser.getAttributeValue(0);
        				}else if (element.equals("temp_f")){ 
        					lx_Weather.current_temp = parser.getAttributeValue(0);  
        				}else if (element.equals("humidity")){ 
        					lx_Weather.current_humidity = parser.getAttributeValue(0);  
        				}else if (element.equals("wind_condition")){ 
        					lx_Weather.current_wind = parser.getAttributeValue(0); 
        				}else if (element.equals("icon")){ 
        					lx_Weather.current_image_url = parser.getAttributeValue(0);  
        					lx_Weather.current_image = GetURLBitmap(lx_Weather.current_image_url);
        				}
        			}
        			
        			if (element.equals("current_conditions") && 
        					eventCode == XmlPullParser.END_TAG){
        				break;
        			}
        		}    		
        	}
        	if (element != null && element.equals("forecast_conditions")){
        		while(true){
        			int eventCode = parser.next();
        			element =  parser.getName();
        			if (eventCode == XmlPullParser.START_TAG){
        				if (element.equals("day_of_week")){
    	            		lx_Weather.day[dayCounter].day_of_week =  parser.getAttributeValue(0);
        				}else if (element.equals("low")){ 
        					lx_Weather.day[dayCounter].low = parser.getAttributeValue(0);  
        				}else if (element.equals("high")){ 
        					lx_Weather.day[dayCounter].high = parser.getAttributeValue(0);  
        				}else if (element.equals("icon")){ 
        					lx_Weather.day[dayCounter].image_url = parser.getAttributeValue(0); 
        					lx_Weather.day[dayCounter].image = GetURLBitmap(lx_Weather.day[dayCounter].image_url);
        				}else if (element.equals("condition")){ 
        					lx_Weather.day[dayCounter].condition = parser.getAttributeValue(0);  
        				}
        			}
        			
        			if (element.equals("forecast_conditions") && 
        					eventCode == XmlPullParser.END_TAG){
        				dayCounter++;
        				break;
        			}
        		}    
        	}
        	
        }       
        is.close();
	}
	
	private static Bitmap GetURLBitmap(String urlString){
		
	    URL url = null;
	    Bitmap bitmap = null;
	    try {
	    	url = new URL("http://www.google.com" + urlString);
	    }
	    catch (MalformedURLException e){
	      e.printStackTrace();
	    }
	    
	    try{
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.connect();
	      InputStream is = conn.getInputStream();
	      bitmap = BitmapFactory.decodeStream(is);
	      is.close();
	    }
	    catch (IOException e){
	      e.printStackTrace();
	    }
	    return bitmap;
	  }

	
}
