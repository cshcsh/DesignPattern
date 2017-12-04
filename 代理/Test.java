package com.java5200.tools;

public class Test implements TestTool{

	public static void main(String[] args) {

		TestTool testTool;
		TestTool.setTool();
		Test test=new Test();
		test.setTool();
		test.setTools();
	}
	
	public static void setTool(){
		System.out.println(".........Tool..........");
	}
	
	public void setTools(){
		System.out.println(".........Tools.........");
	}
}
