package ar.com.factory.intf;

public interface Store extends Stage {

	public double getSize();
	public void moveIn();
	public Dish moveOut();	
	
	@Override
	default String getDescription() {
		return "Store";
	}

}

