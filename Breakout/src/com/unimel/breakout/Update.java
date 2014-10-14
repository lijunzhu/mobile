package com.unimel.breakout;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.Scanner;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Update {
	
	static Boolean update_leaderboard = false;
	static String i;
	static String score;
	
	
	public static Dialog insertName(Context context, String titleText){
    	final Dialog dialog = new Dialog(context, R.style.Widget_AppCompat_Light_PopupMenu);
        dialog.setContentView(R.layout.update_leaderboard);

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_playername_title);
        final EditText playername = (EditText) dialog.findViewById(R.id.playername_edit);
        
        Button ok = (Button) dialog.findViewById(R.id.ok_button);
        
        title.setText(titleText);

        
        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = playername.getText().toString();
                if(name == null){
                	playername.setError("The name cannot be empty.");
                }else{
                    dialog.dismiss();
                }  
                
                update_leaderboard = true;
                i = Integer.toString(GameActivity.rank);
                score = Integer.toString(GameActivity.totalscore);
                
        		Socket s = null;
        		DataOutputStream dout = null;
        		DataInputStream din = null;
        		String msg = "update";
                
                if(update_leaderboard){
                	try {
        				s = new Socket("192.168.1.105", 8787);
        				dout = new DataOutputStream(s.getOutputStream());
        				din = new DataInputStream(s.getInputStream());
        				dout.writeUTF(msg);
        				dout.writeUTF(i);
        				dout.writeUTF(name);
        				dout.writeUTF(score);
        				
  
        			} catch (Exception e) {
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
        });
        
        dialog.show();
        
        return dialog;
    }
	
	
	
	

}
