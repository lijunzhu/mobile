package com.unimel.breakout;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.AbsoluteLayout;
import android.widget.Button;

@SuppressWarnings("deprecation")
public class Brick{
	
	public Button [][]brick;
	public int [][]level;	
	//private static int VISIBLE = 0;
	private static int INVISIBLE = 4;
	private int Row;
	private int Column;
	private int brickwidth,brickheight;
	private int rate = 1;
	byte[] buffer;
	
	public Brick(Context context,AbsoluteLayout layout,int width,int height,int stage) {
				
		try {
			if(stage == 0){
				InputStream in = context.getResources().getAssets().open("0.txt");
				int length = in.available();
				buffer = new byte[length];
				in.read(buffer);
			}
			
			if(stage == 1){
				FileInputStream in = context.openFileInput("1.txt");
				int length = in.available();
				buffer = new byte[length];
				in.read(buffer);
			}
			
			
						
			Row=(buffer[0]-'0')*10+(buffer[1]-'0');
			Column=(buffer[3]-'0')*10+(buffer[4]-'0');
			
			//System.out.println(Row+" "+Column);
			
			level=new int[Row][Column];
			
			int k = 7;
			for (int i = 0; i < Row; i++)
			{
	        	for (int j = 0; j < Column; j++)
	        	{
	        		if (buffer[k] == '\r'||buffer[k] == '\n')
	        		{
	        			k++;
	        			j--;
	        			continue;
	        		}
	        		else if (buffer[k] <= '9'&&buffer[k] >= '0')
	        		{
	        			level[i][j]=buffer[k]-'0';
	        			k++;
	        		}
	        		else
	        		{
	        			k++;
	        			j--;
	        			continue;
	        		}
	        		//System.out.println(i+" "+j+" "+level[i][j]);
	        	}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		brick=new Button[Row][Column];
		brickwidth=width/Column;
		brickheight=(int)(width*rate/Column);
		
		for (int i = 0; i < Row; i++)
		{
        	for (int j = 0; j < Column; j++)
	        {
        		brick[i][j]=new Button(context);
        		brick[i][j].setLayoutParams(new AbsoluteLayout.LayoutParams(
	    				(int)(brickwidth*0.95),
	    				(int)(brickheight*0.95),
	    				j*brickwidth,i*(int)(brickheight)));
        		
        		brick[i][j].setClickable(false);
        		//System.out.println(i+" "+j+" "+level[i][j]);
        		switch(level[i][j])
        		{
        		case 0:
        			brick[i][j].setVisibility(INVISIBLE);
        			break;
        		case 1:
            		brick[i][j].setBackgroundColor(Color.GRAY);
            		break;
        		case 2:
            		brick[i][j].setBackgroundColor(Color.RED);
            		break;
        		case 3:
            		brick[i][j].setBackgroundColor(Color.YELLOW);
            		break;
        		}
	        	layout.addView(brick[i][j]);
	        }
		}
	}

	public void destroy(AbsoluteLayout layout)
	{
		for (int i = 0; i < Row; i++)
		{
        	for (int j = 0; j < Column; j++)
	        {
        		layout.removeView(brick[i][j]);
	        }
		}
	}
	
	public boolean win() {
		for (int i = 0; i < Row; i++)
		{
        	for (int j = 0; j < Column; j++)
	        {
        		if (level[i][j] != 0)
        		{
        			return false;
        		}
	        }
		}
		return true;
	}
	
	public int getRow() {
		return Row;
	}
	
	public int getColumn() {
		return Column;
	}
	
	public int getWidth() {
		return brickwidth;
	}
	
	public int getHeight() {
		return brickheight;
	}
}