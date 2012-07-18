package com.moxisoftworks.beetzgame;

import java.util.Random;
import android.content.res.*;
import android.graphics.*;

public class Element {
	
	//Public Variables
	
	
	
	// Private Variables
	
	private int mX;
	private int mY;
	private int mSpeedX;
	private int mSpeedY;
	private Bitmap mBitmap;
	
	public Element (Resources res, int x, int y) {
		Random rand = new Random();
		mBitmap = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
		mX = x - mBitmap.getWidth() / 2;
		mY = y - mBitmap.getHeight() / 2;
		mSpeedX = rand.nextInt(7) - 3;
		mSpeedY = rand.nextInt(7) - 3;
	}
	
	public void animate(long elapsedTime) {
		mX += mSpeedX * (elapsedTime / 20f);
		mY += mSpeedY * (elapsedTime / 20f);
		checkBorders();
	}
	
	private void checkBorders(){
		if (mX <= 0) {
			mSpeedX = -mSpeedX;
			mX = 0;
		}
		else if (mX + mBitmap.getWidth() >= Panel.mWidth) {
			mSpeedX = -mSpeedX;
			mX = (int) (Panel.mWidth - mBitmap.getWidth());
		}
		if (mY <= 0) {
			mY = 0;
			mSpeedY = -mSpeedY;
		}
		if (mY + mBitmap.getHeight() >= Panel.mHeight) {
			mSpeedY = -mSpeedY;
			mY = (int) (Panel.mHeight - mBitmap.getHeight());
		}
	}
	
	public void doDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap, mX, mY, null);
	}
}
