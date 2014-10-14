package com.unimel.breakout;

import com.unimel.breakout.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends Activity {
    /** Called when the activity is first created. */
    
	private Button begin, download, leaderboard, help, quit;
	public static final String GAME="com.unimel.breakout.GameActivity";
	public static final String LEADER="com.unimel.breakout.LeaderboardActivity";
	public static final String DOWNLOAD = "com.unimel.breakout.DownloadActivity";
	public static final String HELP = "com.unimel.breakout.HelpActivity";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        begin=(Button) findViewById(R.id.begin);
        download=(Button) findViewById(R.id.download);
        leaderboard=(Button) findViewById(R.id.leader_board);
        help= (Button) findViewById(R.id.help);
        quit=(Button) findViewById(R.id.quit);
       
        begin.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
            	Intent intent = new Intent();
            	intent.setAction(GAME);
            	startActivity(intent);
            	//finish();
        	}
        	
        });
        
        download.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v) {
            	Intent intent = new Intent();
            	intent.setAction(DOWNLOAD);
            	startActivity(intent);	
        	}
        	
        });
        
        leaderboard.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
            	Intent intent = new Intent();
            	intent.setAction(LEADER);
            	startActivity(intent);	
        	}
        	
        });
        
        help.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
            	Intent intent = new Intent();
            	intent.setAction(HELP);
            	startActivity(intent);	
        	}
        	
        });
        
        quit.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		finish();
        	}
        	
        });
    }
}