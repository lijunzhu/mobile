package com.unimel.breakout;

import com.unimel.breakout.R;

import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.widget.AbsoluteLayout;
import android.widget.Button;

@SuppressWarnings("deprecation")
public class Ball {
	
	private Button ball;
	int ballr;
    private int Height;
    private int X;
    private int Y;
    private boolean VxFlag = true;
    private boolean VyFlag = false;
	//private static int VISIBLE = 0;
	private static int INVISIBLE = 4;
	//public boolean hitbrick = false;
	public int score = 0;
	public static boolean enlarge_stick = false;
	public static boolean enlarge_ball = false;
    
	public Ball(Context context,AbsoluteLayout layout,int width,int height,int Ballr) {
		
		ballr=Ballr;
		Height=height;
		
		
		ball = new Button(context);
		ball.setBackgroundResource(R.drawable.ball);
		ball.setLayoutParams(new AbsoluteLayout.LayoutParams(
				ballr,
				ballr,
				(width-ballr)/2,(int)(Height*0.9-ballr)));
		ball.setClickable(false);
        layout.addView(ball);
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public void setBallPositionX(int x) {
		X=x-ballr/2;
		Y=(int)(Height*0.9-ballr);
		ball.setLayoutParams(new AbsoluteLayout.LayoutParams(
				ballr,
				ballr,
				X,Y));

	}
	
	public void vsWall(int width,int height) {
		if (X <= 0)
		{
			VxFlag=true;
		}
		
		if (X + ballr >= width)
		{
			VxFlag=false;
		}
		
		if (Y <= 0)
		{
			VyFlag=true;
		}
		
		/*if (Y + ballr >= height)
		{
			VyFlag=false;
		}*/
	}
	
	public void destroy(AbsoluteLayout layout)
	{
        layout.removeView(ball);
	}
	
	public boolean loselife(int height) {
		if (Y + ballr >= height)
		{
			return true;
		}
		return false;
	}
	
	public void vsStick(Stick stick) {
		if (Y + ballr >= stick.getY() && Y +ballr <= stick.getY() + stick.getHeigth())
		{
			if (X + ballr >= stick.getX() && X <= stick.getX() + stick.getWidth())
			{
				VyFlag=false;
			}
		}
	}
	
	public void vsBrick(Brick brick) {
		
		int x1,y1,x2,y2;
		if (!VxFlag)
		{
			if (!VyFlag)
			{
				x1=X+ballr/2;
				y1=Y;
				
				x2=X;
				y2=Y+ballr/2;
				
			}
			else
			{
				x1=X+ballr/2;
				y1=Y+ballr;
				
				x2=X;
				y2=Y+ballr/2;
			}
		}
		else
		{
			if (!VyFlag)
			{
				x1=X+ballr/2;
				y1=Y;
				
				x2=X+ballr;
				y2=Y+ballr/2;
			}
			else
			{
				x1=X+ballr/2;
				y1=Y+ballr;
				
				x2=X+ballr;
				y2=Y+ballr/2;
			}
		}
		if (x1/brick.getWidth() < brick.getColumn() && y1/brick.getHeight() < brick.getRow()&&x1/brick.getWidth() == x2/brick.getWidth()&&y1/brick.getHeight() == y2/brick.getHeight())
		{
			if (brick.level[y1/brick.getHeight()][x1/brick.getWidth()] != 0)
			{	
				if(brick.level[y1/brick.getHeight()][x1/brick.getWidth()] == 2){
					enlarge_stick = true;
				}
				
				if(brick.level[y1/brick.getHeight()][x1/brick.getWidth()] == 3){
					enlarge_ball = true;
					brick.level[y1/brick.getHeight()][x1/brick.getWidth()]--;
				}
								
				brick.level[y1/brick.getHeight()][x1/brick.getWidth()]--;
				//hitbrick = true;
				score = score + 5;
				switch(brick.level[y1/brick.getHeight()][x1/brick.getWidth()])
        		{
        		case 0:
        			brick.brick[y1/brick.getHeight()][x1/brick.getWidth()].setVisibility(INVISIBLE);
        			break;
        		case 1:
        			brick.brick[y1/brick.getHeight()][x1/brick.getWidth()].setBackgroundColor(Color.GRAY);
            		break;
        		}
				VyFlag=!VyFlag;
				VxFlag=!VxFlag;
				return ;
			}
		}
		
		if (x1/brick.getWidth() < brick.getColumn() && y1/brick.getHeight() < brick.getRow())
		{
			if (brick.level[y1/brick.getHeight()][x1/brick.getWidth()] != 0)
			{
				if(brick.level[y1/brick.getHeight()][x1/brick.getWidth()] == 2){
					enlarge_stick = true;
				}
				
				if(brick.level[y1/brick.getHeight()][x1/brick.getWidth()] == 3){
					enlarge_ball = true;
					brick.level[y1/brick.getHeight()][x1/brick.getWidth()]--;
				}
				
				brick.level[y1/brick.getHeight()][x1/brick.getWidth()]--;
				//hitbrick = true;
				score = score + 5;
				switch(brick.level[y1/brick.getHeight()][x1/brick.getWidth()])
        		{
        		case 0:
        			brick.brick[y1/brick.getHeight()][x1/brick.getWidth()].setVisibility(INVISIBLE);
        			break;
        		case 1:
        			brick.brick[y1/brick.getHeight()][x1/brick.getWidth()].setBackgroundColor(Color.GRAY);
            		break;
        		}
				VyFlag=!VyFlag;
				return ;
			}
		}
		
		if (x2/brick.getWidth() < brick.getColumn() && y2/brick.getHeight() < brick.getRow())
		{
			if (brick.level[y2/brick.getHeight()][x2/brick.getWidth()] != 0)
			{
				if(brick.level[y1/brick.getHeight()][x1/brick.getWidth()] == 2){
					enlarge_stick = true;
				}
				
				if(brick.level[y1/brick.getHeight()][x1/brick.getWidth()] == 3){
					enlarge_ball = true;
					brick.level[y1/brick.getHeight()][x1/brick.getWidth()]--;
				}
				
				brick.level[y2/brick.getHeight()][x2/brick.getWidth()]--;
				//hitbrick = true;
				score = score + 5;
				switch(brick.level[y2/brick.getHeight()][x2/brick.getWidth()])
        		{
        		case 0:
        			brick.brick[y2/brick.getHeight()][x2/brick.getWidth()].setVisibility(INVISIBLE);
        			break;
        		case 1:
        			brick.brick[y2/brick.getHeight()][x2/brick.getWidth()].setBackgroundColor(Color.GRAY);
            		break;

        		}
				VxFlag=!VxFlag;
				return ;
			}
			
		}
		
		/*if (!VxFlag)
		{
			
		}
		else
		{
			
		}
		
		if (!VyFlag)
		{
			if (X/brick.getWidth() < brick.getColumn() && Y/brick.getHeight() < brick.getRow())
			{
				if (brick.level[Y/brick.getHeight()][X/brick.getWidth()] != 0)
				{
					brick.level[Y/brick.getHeight()][X/brick.getWidth()]--;
					switch(brick.level[Y/brick.getHeight()][X/brick.getWidth()])
	        		{
	        		case 0:
	        			brick.brick[Y/brick.getHeight()][X/brick.getWidth()].setVisibility(INVISIBLE);
	        			break;
	        		case 1:
	        			brick.brick[Y/brick.getHeight()][X/brick.getWidth()].setBackgroundColor(Color.GRAY);
	            		break;
	        		case 2:
	        			brick.brick[Y/brick.getHeight()][X/brick.getWidth()].setBackgroundColor(Color.RED);
	            		break;
	        		case 3:
	        			brick.brick[Y/brick.getHeight()][X/brick.getWidth()].setBackgroundColor(Color.GREEN);
	            		break;
	        		}
					VyFlag=true;
				}
			}
			
			if ((X+ballr)/brick.getWidth() < brick.getColumn() && Y/brick.getHeight() < brick.getRow())
			{
				if (brick.level[Y/brick.getHeight()][(X+ballr)/brick.getWidth()] != 0)
				{
					brick.level[Y/brick.getHeight()][(X+ballr)/brick.getWidth()]--;
					switch(brick.level[Y/brick.getHeight()][(X+ballr)/brick.getWidth()])
	        		{
	        		case 0:
	        			brick.brick[Y/brick.getHeight()][(X+ballr)/brick.getWidth()].setVisibility(INVISIBLE);
	        			break;
	        		case 1:
	        			brick.brick[Y/brick.getHeight()][(X+ballr)/brick.getWidth()].setBackgroundColor(Color.GRAY);
	            		break;
	        		case 2:
	        			brick.brick[Y/brick.getHeight()][(X+ballr)/brick.getWidth()].setBackgroundColor(Color.RED);
	            		break;
	        		case 3:
	        			brick.brick[Y/brick.getHeight()][(X+ballr)/brick.getWidth()].setBackgroundColor(Color.GREEN);
	            		break;
	        		}
					VyFlag=true;
				}
			}

		}
		else
		{
			if (X/brick.getWidth() < brick.getColumn() && (Y+ballr)/brick.getHeight() < brick.getRow())	
			{
				if (brick.level[(Y+ballr)/brick.getHeight()][X/brick.getWidth()] != 0)
				{
					brick.level[(Y+ballr)/brick.getHeight()][X/brick.getWidth()]--;
					switch(brick.level[(Y+ballr)/brick.getHeight()][X/brick.getWidth()])
	        		{
	        		case 0:
	        			brick.brick[(Y+ballr)/brick.getHeight()][X/brick.getWidth()].setVisibility(INVISIBLE);
	        			break;
	        		case 1:
	        			brick.brick[(Y+ballr)/brick.getHeight()][X/brick.getWidth()].setBackgroundColor(Color.GRAY);
	            		break;
	        		case 2:
	        			brick.brick[(Y+ballr)/brick.getHeight()][X/brick.getWidth()].setBackgroundColor(Color.RED);
	            		break;
	        		case 3:
	        			brick.brick[(Y+ballr)/brick.getHeight()][X/brick.getWidth()].setBackgroundColor(Color.GREEN);
	            		break;
	        		}
					VyFlag=false;
				}
			}
	
			if ((X+ballr)/brick.getWidth() < brick.getColumn() && (Y+ballr)/brick.getHeight() < brick.getRow())
			{
				if (brick.level[(Y+ballr)/brick.getHeight()][(X+ballr)/brick.getWidth()] != 0)
				{
					brick.level[(Y+ballr)/brick.getHeight()][(X+ballr)/brick.getWidth()]--;
					switch(brick.level[(Y+ballr)/brick.getHeight()][(X+ballr)/brick.getWidth()])
	        		{
	        		case 0:
	        			brick.brick[(Y+ballr)/brick.getHeight()][(X+ballr)/brick.getWidth()].setVisibility(INVISIBLE);
	        			break;
	        		case 1:
	        			brick.brick[(Y+ballr)/brick.getHeight()][(X+ballr)/brick.getWidth()].setBackgroundColor(Color.GRAY);
	            		break;
	        		case 2:
	        			brick.brick[(Y+ballr)/brick.getHeight()][(X+ballr)/brick.getWidth()].setBackgroundColor(Color.RED);
	            		break;
	        		case 3:
	        			brick.brick[(Y+ballr)/brick.getHeight()][(X+ballr)/brick.getWidth()].setBackgroundColor(Color.GREEN);
	            		break;
	        		}
					VyFlag=false;
				}
			}

		}*/
	}
	
	public void BallMove(int x,int y) {
		//System.out.println(X+","+Y);
		if (VxFlag)
		{
			setBallPositionXY(X+x,Y);
		}
		else
		{
			setBallPositionXY(X-x,Y);
		}
		
		if (VyFlag)
		{
			setBallPositionXY(X,Y+y);
		}
		else
		{
			setBallPositionXY(X,Y-y);
		}
	}
	
	public void setBallPositionXY(int x,int y) {
		X=x;
		Y=y;
		ball.setLayoutParams(new AbsoluteLayout.LayoutParams(
				ballr,
				ballr,
				X,Y));
	}
	


}