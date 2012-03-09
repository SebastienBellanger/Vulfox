package com.vulfox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vulfox.entity.IDrawable;
import com.vulfox.entity.IEntity;
import com.vulfox.entity.IUpdatable;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class EntityManagerComponent extends ScreenComponent {

	private HashMap<String, IEntity> mEntities = new HashMap<String, IEntity>();
	
	private List<IUpdatable> mUpdatable = new ArrayList<IUpdatable>();
	
	private List<IDrawable> mDrawable = new ArrayList<IDrawable>();
	
	public void addEntity(IEntity entity) {		
		mEntities.put(entity.getIdentifier(), entity);		
		mUpdatable.addAll(entity.findAll(IUpdatable.class));		
		mDrawable.addAll(entity.findAll(IDrawable.class));
	}
	
	@Override
	void handleActionDown(MotionEvent motionEvent, boolean insideConponent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean handleActionUp(MotionEvent motionEvent, boolean insideConponent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void handleActionMove(MotionEvent motionEvent, boolean insideConponent) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	void update(float timeStep) {
		for (IUpdatable updatableEntity : mUpdatable) {
			updatableEntity.update(timeStep);
		}		
	}
	
	@Override
	void draw(Canvas canvas) {
		for (IDrawable drawableEntity : mDrawable) {
			drawableEntity.draw(canvas);
		}
	}

}
