package com.moxisoftworks.beetzgame;


import java.util.ArrayList;
import android.content.*;
import android.graphics.*;
import android.view.*;

/** 
 * Panel.Java
 * @author Jeremy
 * 
 * Purpose:  Responsible for handling game loop, game states, input-related events, as well as drawing
 *
 */

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
	
	// Public Variables
	public Bitmap mBitmap;
	public static float mWidth;
	public static float mHeight;
	
	// Private Variables
	private ViewThread mThread;
	private ArrayList<Element> mElements = new ArrayList<Element>();
	private int mElementNumber = 0;
	private Paint mPaint = new Paint();
	
	
	//Constructor
	public Panel(Context context) {
	    super(context);
	    getHolder().addCallback(this);
	    mThread = new ViewThread(this);
	    mPaint.setColor(Color.WHITE);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
		mWidth = width;
		mHeight = height;
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!mThread.isAlive()) {
			mThread = new ViewThread(this);
			mThread.setRunning(true);
			mThread.start();
		}		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder){
		if (mThread.isAlive()) {
			mThread.setRunning(false);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		// Synchronized prevents concurrent modification exception
		// From occurring on mElements
		synchronized (mElements){
			mElements.add(new Element(getResources(), (int) event.getX(), (int) event.getY()));
			mElementNumber = mElements.size();
		}
		return super.onTouchEvent(event);
	}
	
	public void animate(long elapsedTime) {
		synchronized (mElements){
			for (Element element : mElements) {
				element.animate(elapsedTime);
			}
		}
	}
	
	public void doDraw(long elapsed, Canvas canvas) {
       canvas.drawColor(Color.BLACK);
       for (Element element : mElements) {
    	   element.doDraw(canvas);
       }
       canvas.drawText("FPS: " + Math.round(1000f / elapsed) + " Elements: " + mElementNumber, 10, 10, mPaint);
	}		
}

