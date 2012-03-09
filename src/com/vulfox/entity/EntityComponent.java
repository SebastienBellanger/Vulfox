package com.vulfox.entity;

public abstract class EntityComponent implements IEntityComponent {

	protected IEntity mParent;

	@Override
	public void setParent(IEntity entity) {
		mParent = entity;
	}
	
	@Override
	public IEntity getParent() {
		return mParent;
	}

	@Override
	public abstract void initialize();

	@Override
	public abstract void update(float timeStep);

}
