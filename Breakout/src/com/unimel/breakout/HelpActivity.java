package com.unimel.breakout;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends Activity{
	public static final String ENCODING = "UTF-8";
	TextView Help;
	 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        
        Help = (TextView) findViewById(R.id.help_info);
        Help.setText(getFromAsset("help.txt"));
	}
    
	public String getFromAsset(String fileName){
    	String result="";
    	try{
    		InputStream in = getResources().getAssets().open(fileName);		
    		int length = in.available();									
        	byte [] buffer = new byte[length];								
        	in.read(buffer);												
        	result = EncodingUtils.getString(buffer, ENCODING);				
    	}
    	catch(Exception e){
    		e.printStackTrace();											
    	}
    	return result;
    }
    
}
