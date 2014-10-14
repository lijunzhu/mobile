package com.unimel.breakout;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;

import com.unimel.breakout.R;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DownloadActivity extends Activity implements OnClickListener{
	Button button1;
	Button button2;
	static TextView textView;
	String msg1 = "Get 2nd level.";
	String msg2 = "Get 3rd level.";
	String newmap = null;
	private Context context;
	public static Boolean havesecondlevel = false;
	public static Boolean havethirdlevel = false;

	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        button1 = (Button) findViewById(R.id.download);
        button2 = (Button) findViewById(R.id.download11);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        
        context = this;
    }
    
	public void onClick(View v) {
		Socket s = null;
		DataOutputStream dout = null;
		DataInputStream din = null;
		if(v == button1){
			try {
				s = new Socket("192.168.1.105", 8787);
				dout = new DataOutputStream(s.getOutputStream());
				din = new DataInputStream(s.getInputStream());
				
				dout.writeUTF(msg1);
				newmap = din.readUTF();
				
				writeFileData("1.txt", newmap);
				Toast.makeText(DownloadActivity.this, getString(R.string.download), Toast.LENGTH_LONG).show();
				havesecondlevel = true;
			} catch (Exception e) {
				Toast.makeText(DownloadActivity.this, getString(R.string.Nonetwork), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			} finally {
				try{
					if(dout != null){
						dout.close();
					}
					if(din != null){
						din.close();
					}
					if(s != null){
						s.close();
					}					
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		if(v == button2){
			try {
				s = new Socket("192.168.1.105", 8787);
				dout = new DataOutputStream(s.getOutputStream());
				din = new DataInputStream(s.getInputStream());
				
				dout.writeUTF(msg2);
				newmap = din.readUTF();
				
				writeFileData("2.txt", newmap);
				Toast.makeText(DownloadActivity.this, getString(R.string.download), Toast.LENGTH_LONG).show();
				havethirdlevel = true;
			} catch (Exception e) {
				Toast.makeText(DownloadActivity.this, getString(R.string.Nonetwork), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			} finally {
				try{
					if(dout != null){
						dout.close();
					}
					if(din != null){
						din.close();
					}
					if(s != null){
						s.close();
					}					
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void writeFileData(String fileName,String message){
    	try{
    		FileOutputStream fout = context.openFileOutput(fileName, MODE_PRIVATE);
    		byte [] bytes = message.getBytes();
    		fout.write(bytes);
    		fout.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
	

}
