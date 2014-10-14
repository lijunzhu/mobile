package com.unimel.breakout;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

import com.unimel.breakout.R;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LeaderboardActivity extends Activity implements OnClickListener{
	
	Button button1;
	String filename = "Leaderboard.txt";
	String leaderboard = null;
	String msg = "Get leaderboard.";
	private static Context context;
	public static boolean get_leaderboard = false;
	public static String[][] Leaderboard = new String[100][2];
	static TextView Name1,Name2,Name3,Name4,Name5,Name6,Name7,Name8,Name9,Name10,
					Score1,Score2,Score3,Score4,Score5,Score6,Score7,Score8,Score9,Score10;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        button1 = (Button) findViewById(R.id.Leader_button);
        Name1 = (TextView) findViewById(R.id.Name1);
        Name2 = (TextView) findViewById(R.id.Name2);
        Name3 = (TextView) findViewById(R.id.Name3);
        Name4 = (TextView) findViewById(R.id.Name4);
        Name5 = (TextView) findViewById(R.id.Name5);
        Name6 = (TextView) findViewById(R.id.Name6);
        Name7 = (TextView) findViewById(R.id.Name7);
        Name8 = (TextView) findViewById(R.id.Name8);
        Name9 = (TextView) findViewById(R.id.Name9);
        Name10 = (TextView) findViewById(R.id.Name10);
        Score1 = (TextView) findViewById(R.id.Score1);
        Score2 = (TextView) findViewById(R.id.Score2);
        Score3 = (TextView) findViewById(R.id.Score3);
        Score4 = (TextView) findViewById(R.id.Score4);
        Score5 = (TextView) findViewById(R.id.Score5);
        Score6 = (TextView) findViewById(R.id.Score6);
        Score7 = (TextView) findViewById(R.id.Score7);
        Score8 = (TextView) findViewById(R.id.Score8);
        Score9 = (TextView) findViewById(R.id.Score9);
        Score10 = (TextView) findViewById(R.id.Score10);

        button1.setOnClickListener(this);
        
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
				dout.writeUTF(msg);
				for(int i = 0; i < 10; i++){
					for(int j = 0; j < 2; j++){
						Leaderboard[i][j] = din.readUTF();
					}
				}
				Name1.setText(Leaderboard[0][0]);
				Score1.setText(Leaderboard[0][1]);
				Name2.setText(Leaderboard[1][0]);
				Score2.setText(Leaderboard[1][1]);
				Name3.setText(Leaderboard[2][0]);
				Score3.setText(Leaderboard[2][1]);
				Name4.setText(Leaderboard[3][0]);
				Score4.setText(Leaderboard[3][1]);
				Name5.setText(Leaderboard[4][0]);
				Score5.setText(Leaderboard[4][1]);
				Name6.setText(Leaderboard[5][0]);
				Score6.setText(Leaderboard[5][1]);
				Name7.setText(Leaderboard[6][0]);
				Score7.setText(Leaderboard[6][1]);
				Name8.setText(Leaderboard[7][0]);
				Score8.setText(Leaderboard[7][1]);
				Name9.setText(Leaderboard[8][0]);
				Score9.setText(Leaderboard[8][1]);
				Name10.setText(Leaderboard[9][0]);
				Score10.setText(Leaderboard[9][1]);
				//textView.setText(leaderboard);
				//writeFileData(filename, leaderboard);
				get_leaderboard = true;
				//getLeaderbord();
			} catch (Exception e) {
				Toast.makeText(LeaderboardActivity.this, getString(R.string.Nonetwork), Toast.LENGTH_LONG).show();
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
