package com.vulfox.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Canvas;

public class Entity implements IEntity {

	protected String mIdentifier;
	
	protected HashMap<Class<? extends IEntityComponent>, IEntityComponent> mComponents = new HashMap<Class<? extends IEntityComponent>, IEntityComponent>();

	public Entity(String identifier) {
		mIdentifier = identifier;
	}
	
	@Override
	public String getIdentifier() {
		return mIdentifier;
	}
	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IEntityComponent> List<T> findAll(Class<T> klass) {
				
		List<T> components = new ArrayList<T>();				
		
		for (IEntityComponent entityComponent : mComponents.values()) {					
			if(klass.isInstance(entityComponent)) {
				T instance = (T) entityComponent;			
				if(instance != null)
				{
					components.add(instance);
				}
			}			
		}
		
		return components;
	}
	
	@Override
	public <T extends IEntityComponent> boolean has(Class<T> klass) {
		return mComponents.containsKey(klass);		
	}

	@Override
	public <T extends IEntityComponent> T add(T entityComponent) {
		return add(entityComponent, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IEntityComponent> T add(T entityComponent,
			boolean replace) {

		Class<T> klass = (Class<T>) entityComponent.getClass();

		if (mComponents.containsKey(klass)) {
			if (replace) {
				mComponents.remove(klass);
			} else {
				return (T) find(entityComponent.getClass());
			}
		}

		mComponents.put(klass, entityComponent);

		// Do initialization here so that cyclic dependencies can be resolved
		entityComponent.setParent(this);
		entityComponent.initialize();
		
		return entityComponent;
	}

	@Override
	public <T extends IEntityComponent> boolean remove(Class<T> klass) {
		
		if (mComponents.containsKey(klass)) {
			mComponents.remove(klass);
			return true;
		}

		return false;
	}

}
