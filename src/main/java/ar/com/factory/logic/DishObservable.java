package ar.com.factory.logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import ar.com.factory.intf.Dish;

public class DishObservable {
	
	private Dish dish;
	
	private PropertyChangeSupport support;
	 
    public DishObservable() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
 
    public void setDish(Dish value) {
        support.firePropertyChange("dish", this.dish, value);
        this.dish = value;
    }
    
}