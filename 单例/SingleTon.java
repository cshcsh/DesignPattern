package com.java5200.pattern.singleton;

/**
 * 单例设计模式--饿汉式
 * @author CSH
 *
 */
public class SingleTon {
	
	private SingleTon(){}//只能有一个唯一实例，外部不可调用这个构造方法
	
	private static SingleTon singleTon=new SingleTon();//自己创建自己的唯一实例
	
	public static SingleTon getInstance(){//对外部提供这一实例
		return singleTon;
	}
}

/**
 * 单例设计模式--懒汉式
 * @author CSH
 *
 */
/*public class SingleTon {
	
	private SingleTon(){}//只能有一个唯一实例，外部不可调用这个构造方法
	
	private static SingleTon singleTon=null;//自己创建自己的唯一实例
	
	public static synchronized SingleTon getInstance(){//对外部提供这一实例,synchronized
		if(singleTon==null){
			singleTon=new SingleTon();
		}
		return singleTon;
	}
}*/
