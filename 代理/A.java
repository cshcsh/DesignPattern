package com.java5200.test001;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class A {

	private int m;
	
	public A(){
		System.out.println("无参构造方法");
	}
	public A(Integer m){
		System.out.println("有参构造方法");
	}
	
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	
	public void fun(){
		System.out.println("方法1");
	}
	public void fun2(){
		System.out.println("方法2");
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		Class classInfo=Class.forName("com.java5200.test001.A");
		
		Constructor constructors[]=classInfo.getConstructors();
		for(int i=0;i<constructors.length;i++){
			System.out.println(constructors[i].toString());
		}
		
		constructors[0].newInstance();
		constructors[1].newInstance(new Object[]{10});
		
		Field fields[]=classInfo.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			System.out.println(fields[i].toString());
		}
		
		Method methods[]=classInfo.getDeclaredMethods();
		for(int i=0;i<methods.length;i++){
			System.out.println(methods[i].toString());
		}
		
		Constructor constructor=classInfo.getConstructor();
		Object object=constructor.newInstance();
		
		//constructor=classInfo.getConstructor(new Class[]{Integer.class});
		//constructor.newInstance(new Object[]{20});
		
		Method method=classInfo.getMethod("fun");
		method.invoke(object);
	}

}
