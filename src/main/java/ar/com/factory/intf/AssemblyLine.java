package ar.com.factory.intf;

public interface AssemblyLine extends Stage {

	public void put();
	public void take(Dish dish);
	boolean isDone(Dish dish);

	@Override
	default String getDescription() {
		return "AssemblyLine";
	}

}