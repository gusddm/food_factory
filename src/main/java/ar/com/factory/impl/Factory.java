package ar.com.factory.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ar.com.factory.intf.Dish;

public class Factory {

	private Logger logger = Logger.getLogger(Factory.class);

	public void init() {
		
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));

		//Blocking queues, that simulate different stages of the production of Dishes.
		LinkedBlockingDeque<Dish> assemblyToStoreQueue = new LinkedBlockingDeque<Dish>();
		LinkedBlockingDeque<Dish> storeToOvenQueue = new LinkedBlockingDeque<Dish>();
		
		//This executors proccess the data of the different queues, in a asynchronous manner, respecting the constraints defined by the underlying blocking queues.
        ExecutorService pes = Executors.newFixedThreadPool(1);
        ExecutorService ces = Executors.newFixedThreadPool(2);
        
		Runnable assemblyStoreProducer = () -> {
			while (true) {
				try {
					Dish dish = new DishImpl("Pasta Nro." + System.currentTimeMillis() % 1000);				
					assemblyToStoreQueue.put(dish);
					logger.info("Dish " + dish.getDescription() + " PRODUCED");
					Thread.sleep(200);				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};

		Runnable assemblyStoreConsumer = () -> {
			while (true) {
				try {
					Dish dish = assemblyToStoreQueue.take();
					logger.info("Dish " + dish.getDescription() + " in STORE");
					Thread.sleep(200);										
					storeToOvenQueue.put(dish);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		Runnable storeOvenConsumer = () -> {
			while (true) {
				try {
					Dish dish = storeToOvenQueue.take();					
					logger.info("Dish " + dish.getDescription() + " in OVEN");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		pes.submit(assemblyStoreProducer);
		ces.submit(assemblyStoreConsumer);
		ces.submit(storeOvenConsumer);
        
        pes.shutdown();
        ces.shutdown();

	}
	
	public static void main(String[] args) {
		Factory f = new Factory();
		f.init();
	}

}