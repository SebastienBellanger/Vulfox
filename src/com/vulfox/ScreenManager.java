package com.vulfox;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class ScreenManager {

	/**
	 * Application context
	 */
	private Context mContext;
	
	/**
	 * List of all the screens that are present in the game
	 * Used as a stack to keep track of the top screen 
	 */
	private LinkedList<Screen> mScreenList;

	/**
	 * Width of the draw surface
	 */
	private int mWidth;
	
	/**
	 * Height of the draw surface
	 */
	private int mHeight;

	/**
	 * Indicates if the manager has been initialized
	 */
	private boolean mInitialized;

	/**
	 * Creates a new uninitialized screen manager
	 * @param context Application context
	 */
	public ScreenManager(Context context) {
		mContext = context;
		mScreenList = new LinkedList<Screen>();
		mInitialized = false;
	}
	
	/**
	 * Tells if the manager has been initialized
	 * @return True if initialized
	 */
	public boolean isInitialized()
	{
		return mInitialized;
	}

	/**
	 * Initializes the screen manager and any screens that have been added
	 * @param width Width of the draw surface
	 * @param height Height of the draw surface
	 */
	public synchronized void initialize(int width, int height) {
		mWidth = width;
		mHeight = height;
		mInitialized = true;

		for (Screen screen : mScreenList) {
			screen.initialize(mWidth, mHeight, mContext, this);
		}
	}

	/**
	 * Adds a screen to the top of the screen stack
	 * @param screen A screen implementation
	 */
	public synchronized void addScreen(Screen screen) {
		if (mInitialized) {
			screen.initialize(mWidth, mHeight, mContext, this);
		}

		mScreenList.addLast(screen);
	}

	/**
	 * Relays the motion event to the top screen 
	 * @param motionEvent
	 */
	public void handleInput(MotionEvent motionEvent) {
		if(!mInitialized) {
			return;
		}
		
		if (mScreenList.size() > 0) {
			Screen topScreen = mScreenList.getLast();
			topScreen.handleInput(motionEvent);
		}
	}

	/**
	 * Updates the top screen
	 * @param timeStep Time since the last frame in milliseconds
	 */
	public void update(float timeStep) {
		if(!mInitialized) {
			return;
		}
		
		if (mScreenList.size() > 0) {
			Screen topScreen = mScreenList.getLast();
			topScreen.update(timeStep);
		}
	}

	/**
	 * Draws the top screen
	 * @param canvas Canvas to draw to
	 */
	public void draw(Canvas canvas) {
		if(!mInitialized) {
			return;
		}
		
		if (mScreenList.size() > 0) {
			Screen topScreen = mScreenList.getLast();
			topScreen.draw(canvas);
		}
	}
}
