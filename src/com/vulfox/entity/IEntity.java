package com.vulfox.entity;

import java.util.List;

import android.graphics.Canvas;

public interface IEntity {

	/**
	 * Get the identifier of the Entity
	 * 
	 * @return Identifier name
	 */
	public String getIdentifier();

	/**
	 * Find a component based on the class type. If the component doesn't exist
	 * it will try to create a new one using the default constructor.
	 * 
	 * @param klass
	 *            Component class
	 * @return An instance of the component or null if it could not find or
	 *         create the component
	 */
	public <T extends IEntityComponent> T find(Class<T> klass);

	/**
	 * Find all components based on the specified class
	 * 
	 * @param klass
	 *            Component class
	 * @return A list of components matching the class
	 */
	public <T extends IEntityComponent> List<T> findAll(Class<T> klass);
	
	/**
	 * Checks if the entity has a component
	 * 
	 * @param klass Component class
	 * @return True if the component is in the entity
	 */
	public <T extends IEntityComponent> boolean has(Class<T> klass);

	/**
	 * Adds a component to the entity. The component will not be replaced if it
	 * already exists.
	 * 
	 * @param entityComponent
	 *            Component to add
	 * @return An instance of the component
	 */
	public <T extends IEntityComponent> T add(T entityComponent);

	/**
	 * Adds a component to the entity. The component will be replaced if
	 * "replace" is true
	 * 
	 * @param entityComponent
	 *            Component to add
	 * @param replace
	 *            Indicates if the component should be replaced if it already
	 *            exists
	 * @return An instance of the component
	 */
	public <T extends IEntityComponent> T add(T entityComponent, boolean replace);

	/**
	 * Removes a component
	 * 
	 * @param klass
	 *            Component class
	 * @return True if the component was removed
	 */
	public <T extends IEntityComponent> boolean remove(Class<T> klass);

}
