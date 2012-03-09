package com.vulfox.entity;

import java.util.List;

import android.graphics.Canvas;

public interface IEntity {
	
	public String getIdentifier();

	public <T extends IEntityComponent> T find(Class<T> klass);
	
	public <T extends IEntityComponent> List<T> findAll(Class<T> klass);
	
	public <T extends IEntityComponent> void add(T entityComponent);
	
	public <T extends IEntityComponent> void add(T entityComponent, boolean replace);
	
	public <T extends IEntityComponent> boolean remove(Class<T> klass);
		
}
