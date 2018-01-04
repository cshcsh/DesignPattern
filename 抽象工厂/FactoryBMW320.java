package com.java5200.pattern.abstractfactory;

public class FactoryBMW320 implements AbstractFactory {

	@Override
	public Engine createEngine() {
		// TODO Auto-generated method stub
		return (Engine) new EngineA();
	}

	@Override
	public Aircondition createAircondition() {
		// TODO Auto-generated method stub
		return (Aircondition) new AirconditionA();
	}

}
