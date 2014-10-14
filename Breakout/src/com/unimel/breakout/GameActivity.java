package com.unimel.breakout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.unimel.breakout.R;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	public int width;
	public int height;
	private Context context;
	private Brick brick;
	public static Stick stick;
	private Ball ball;
	private AbsoluteLayout layout;
	private boolean begin = false;
	private boolean start = false;
	private int level = 0;
	private int Level = 0;
	TextView Score;
	TextView Lives;
	TextView Next;
	TextView Rank;
	static int totalscore;
	int score;
	int nextscore;
	int lives = 3;
	static int rank;
	public String[][] leaderboard = new String[100][3];
	//public boolean get_leaderboard = false;
	
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager WM = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		WM.getDefaultDisplay().getMetrics(metrics);
		width=metrics.widthPixels;
		height=metrics.heightPixels-180;
        
		layout = (AbsoluteLayout)findViewById(R.id.frame);
	
		context=this;
        
		Score = (TextView) this.findViewById(R.id.score);
		Lives = (TextView) this.findViewById(R.id.lives);
		Next = (TextView) this.findViewById(R.id.nextscore);
		Rank = (TextView) this.findViewById(R.id.rank);
		
		handler.post(updateThread);
	}
	
	Handler handler = new Handler();
    Runnable updateThread = new Runnable() {
    	public void run() {
    				
    		for(int i = 0; i < 10; i++){
    			leaderboard[i][0] = LeaderboardActivity.Leaderboard[i][0];
    			leaderboard[i][1] = LeaderboardActivity.Leaderboard[i][1];
    		}
    		
    		if (!start&&level <= Level)
    		{	
    			if(DownloadActivity.havesecondlevel){
    				Level = 1;
    			}else if(DownloadActivity.havethirdlevel){
    				Level = 2;
				}
    			
				brick = new Brick(context,layout,width,height, level++);
    			
    			stick = new Stick(context,layout,width,height,100,20);
    			
    			ball = new Ball(context,layout,width,height,20);
    			
    			start=true;
    		}
    		else if (!start&&level > Level)
    		{

    		}
    		else if (begin)
    		{
    			ball.vsWall(width,height);
    			ball.vsStick(stick);
    			ball.vsBrick(brick);
    			ball.BallMove(10, 10);
    			
    			if(Ball.enlarge_stick == true){
    				stick.stickwidth = 200;
    			}
    			
    			if(Ball.enlarge_ball == true){
    				ball.ballr = 40;
    			}
    			
    			getscore();
    			getlives();
    			
    			if(LeaderboardActivity.get_leaderboard == true){
    				get_next_score_and_rank();

    			}
    			
    			
    			if (ball.loselife(height))
    			{
    				lives = lives -1;
    				Toast.makeText(GameActivity.this, getString(R.string.loselife), Toast.LENGTH_SHORT).show();
    				begin=false;
    			}
    			
    			if(lives == 0){
    				Lives.setText(Integer.toString(lives));    				
    				Toast.makeText(GameActivity.this, getString(R.string.lose), Toast.LENGTH_SHORT).show();
    				brick.destroy(layout);
    				stick.destroy(layout);
    				ball.destroy(layout);
    				begin=false;
    				start=false;
    				Ball.enlarge_stick = false;
    				Ball.enlarge_ball = false;
    				if(LeaderboardActivity.get_leaderboard == true){
    					if(totalscore >= Integer.parseInt(leaderboard[9][1])){
        					Update.insertName(context, "Congtatulations! Your score is in the top10, please enter your name");
        				}
    				}
    				
    			}
    			
    			if (brick.win())
    			{	
    				if(level == Level + 1&&LeaderboardActivity.get_leaderboard == true){
    					if(totalscore >= Integer.parseInt(leaderboard[9][1])){
        					Update.insertName(context, "Congtatulations! Your score is in the top10, please enter your name");
        				}
    				}
    				Toast.makeText(GameActivity.this, getString(R.string.win), Toast.LENGTH_SHORT).show();
    				brick.destroy(layout);
    				stick.destroy(layout);
    				ball.destroy(layout);
    				begin=false;
    				start=false;
    				score = ball.score;
    				Ball.enlarge_stick = false;
    				Ball.enlarge_ball = false;

    			}
    			
    			//System.out.println(ball.getX()+""+ball.getY());
    		}

    		handler.postDelayed(updateThread, 30);
    	}
    	
    };
    
    protected void onPause(){
    	handler.removeCallbacks(updateThread);
    	super.onPause();
    }
    
    protected void onResume() {
    	handler.post(updateThread);
    	super.onResume();
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
    	if(keyCode == KeyEvent.KEYCODE_BACK){
        	handler.removeCallbacks(updateThread);
    		AlertDialog.Builder alert=new AlertDialog.Builder(this);
    		alert.setMessage(getString(R.string.quitalert));
    		alert.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
    			@Override
				public void onClick(DialogInterface dialog, int whichButton) {
    				finish();
				}
			});
    		alert.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
    			@Override
				public void onClick(DialogInterface dialog, int whichButton) {
    		    	handler.post(updateThread);
				}
			});
    		alert.show();
    	}
    	
		return true;
    }
	
	public boolean onTouchEvent(MotionEvent event) {
    	//System.out.println("ball");
    	if (event.getAction() == MotionEvent.ACTION_DOWN)
    	{
    		//System.out.println(event.getX()+","+event.getY());
    		stick.setStickPosition((int)event.getX());
    		if (!begin)
    		{
    			ball.setBallPositionX((int)event.getX());
    		}
    	}
    	if (event.getAction() == MotionEvent.ACTION_MOVE)
    	{
    		//System.out.println(event.getX()+","+event.getY());
    		stick.setStickPosition((int)event.getX());
    		if (!begin)
    		{
    			ball.setBallPositionX((int)event.getX());
    		}
    	}
    	if (event.getAction() == MotionEvent.ACTION_UP)
    	{
    		//System.out.println(event.getX()+","+event.getY());
    		if (!begin)
    		{
    			begin=true;
    		}
    	}
    	return super.onTouchEvent(event);
	}
	
	public void getscore() {
		totalscore = ball.score + score;
		this.Score.setText(Integer.toString(totalscore));
	}
	
	public void getlives(){
		this.Lives.setText(Integer.toString(lives));
	}
	
	
	public void get_next_score_and_rank(){
		nextscore = Integer.parseInt(leaderboard[9][1]);
		for(int i = 0; i < 9; i++){
			if(totalscore < Integer.parseInt(leaderboard[i][1])
					&&totalscore >= Integer.parseInt(leaderboard[i+1][1]))
			{
				nextscore = Integer.parseInt(leaderboard[i][1]);
				rank = i + 2;
			}else if(totalscore > Integer.parseInt(leaderboard[0][1])){
				nextscore = totalscore;
				rank = i + 1;
			}
		}
		this.Next.setText(Integer.toString(nextscore));
		if(totalscore >= Integer.parseInt(leaderboard[9][1])){			
			this.Rank.setText(Integer.toString(rank));
		}
		
	}
	
}