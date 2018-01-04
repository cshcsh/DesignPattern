package com.java5200.pattern.abstractfactory;

public class FactoryBMW523 implements AbstractFactory {

	@Override
	public Engine createEngine() {
		// TODO Auto-generated method stub
		return (Engine) new EngineB();
	}

	@Override
	public Aircondition createAircondition() {
		// TODO Auto-generated method stub
		return (Aircondition) new AirconditionB();
	}

}
