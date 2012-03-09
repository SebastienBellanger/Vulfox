package com.vulfox.entity;

import java.util.HashMap;

import android.graphics.Canvas;

public class Entity implements IEntity {

	protected HashMap<Class<? extends IEntityComponent>, IEntityComponent> mComponents = new HashMap<Class<? extends IEntityComponent>, IEntityComponent>();

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IEntityComponent> T find(Class<T> klass) {

		// Try to create a new instance
		if(!mComponents.containsKey(klass)) {
			try {
				add(klass.newInstance());
			} catch (Exception e) {
				
			}
		}
		
		if (mComponents.containsKey(klass)) {
			return (T) mComponents.get(klass);
		}
		
		return null;
	}

	@Override
	public <T extends IEntityComponent> void add(T entityComponent) {
		add(entityComponent, true);
	}

	@Override
	public <T extends IEntityComponent> void add(T entityComponent,
			boolean replace) {

		@SuppressWarnings("unchecked")
		Class<T> klass = (Class<T>) entityComponent.getClass();

		if (mComponents.containsKey(klass)) {
			if (replace) {
				mComponents.remove(klass);
			} else {
				return;
			}
		}

		mComponents.put(klass, entityComponent);

		// Do initialization here so that cyclic dependencies can be resolved
		entityComponent.setParent(this);
		entityComponent.initialize();
	}

	@Override
	public <T extends IEntityComponent> boolean remove(Class<T> klass) {
		if (mComponents.containsKey(klass)) {
			mComponents.remove(klass);
			return true;
		}

		return false;
	}

	@Override
	public void update(float timeStep) {

		for (IEntityComponent entityComponent : mComponents.values()) {
			entityComponent.update(timeStep);
		}
		
	}

	@Override
	public void draw(Canvas canvas) {
		
		for (IEntityComponent entityComponent : mComponents.values()) {
			
			if(entityComponent instanceof IDrawable) {				
				IDrawable drawable = (IDrawable)entityComponent;				
				drawable.draw(canvas);
			}
			
		}
		
	}

}
