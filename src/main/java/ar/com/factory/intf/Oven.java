package ar.com.factory.intf;

import ar.com.factory.logic.DishObservable;

public interface Oven extends Stage {
	public double getSize();
	public void put(Dish dish);
	public Dish take(Dish dish);
	public void cook(double time);
	public void cook(double time, DishObservable listener);
	
	@Override
	default String getDescription() {
		return "Oven";
	}
	
}
