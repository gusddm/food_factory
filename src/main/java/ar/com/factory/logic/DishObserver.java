package ar.com.factory.logic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import ar.com.factory.intf.Dish;

public class DishObserver implements PropertyChangeListener {
	
	private Dish dish;

	public void propertyChange(PropertyChangeEvent evt) {
		this.setDish((Dish) evt.getNewValue());
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	public Dish getDish() {
		return dish;
	}

}