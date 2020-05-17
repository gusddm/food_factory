package ar.com.factory.impl;

import ar.com.factory.intf.Dish;

public class DishImpl implements Dish {
	
	private String description;
	
	public DishImpl(String description) {
		this.description = description;
	}

	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public double getCookingTime() {
		// TODO Auto-generated method stub
		return 10;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

}
