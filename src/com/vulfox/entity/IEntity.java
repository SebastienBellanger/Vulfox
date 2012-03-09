package com.vulfox.entity;

import android.graphics.Canvas;

public interface IEntity {

	public <T extends IEntityComponent> T find(Class<T> klass);
	
	public <T extends IEntityComponent> void add(T entityComponent);
	
	public <T extends IEntityComponent> void add(T entityComponent, boolean replace);
	
	public <T extends IEntityComponent> boolean remove(Class<T> klass);
	
	public void update(float timeStep);
	
	public void draw(Canvas canvas);
	
}
