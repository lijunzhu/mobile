package com.unimel.breakout;

import android.content.Context;
import android.widget.AbsoluteLayout;
import android.widget.Button;

@SuppressWarnings("deprecation")
public class Stick {
	
    private Button stick = null;
    int stickwidth;
	private int stickheight;
    private int Height;
    private int X;
    private int Y;
    
	public Stick(Context context,AbsoluteLayout layout,int width,int height,int Stickwidth,int Stickheight) {
		
		stickwidth=Stickwidth;
		stickheight=Stickheight;
		Height=height;
		
        stick = new Button(context);
		stick.setLayoutParams(new AbsoluteLayout.LayoutParams(
				stickwidth,
				stickheight,
				(width-stickwidth)/2,(int)(height*0.9)));
		stick.setClickable(false);
        layout.addView(stick);
        
    }
	
	public void setStickPosition(int x) {
		X=x-stickwidth/2;
		Y=(int)(Height*0.9);
		stick.setLayoutParams(new AbsoluteLayout.LayoutParams(
				stickwidth,
				stickheight,
				X,Y));
	}
	
	public void destroy(AbsoluteLayout layout)
	{
        layout.removeView(stick);
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public int getWidth() {
		return stickwidth;
	}
	
	public int getHeigth() {
		return stickheight;
	}
	
	public Button getStick() {
		return stick;
	}
}