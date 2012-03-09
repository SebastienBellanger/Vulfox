package com.vulfox.entity;

public interface IEntityComponent {
	
	public void setParent(IEntity entity);
	
	public IEntity getParent();
	
	public void initialize();
	
}
