package com.java5200.date0529;

public class Car implements Runner {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("start");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("run");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("stop");
	}

	public void fillFuel(){
		System.out.println("fillFuel");
	}
	
	public void crack(){
		System.out.println("crack");
	}
}
