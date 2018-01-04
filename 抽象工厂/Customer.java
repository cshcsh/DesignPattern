package com.java5200.pattern.abstractfactory;

public class Customer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FactoryBMW320 factoryBMW320=new FactoryBMW320();
		factoryBMW320.createEngine();
		factoryBMW320.createAircondition();
		
		FactoryBMW523 factoryBMW523=new FactoryBMW523();
		factoryBMW523.createEngine();
		factoryBMW523.createAircondition();
	}

}
