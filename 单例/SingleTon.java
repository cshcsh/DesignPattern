package com.java5200.pattern.singleton;

/**
 * �������ģʽ--����ʽ
 * @author CSH
 *
 */
public class SingleTon {
	
	private SingleTon(){}//ֻ����һ��Ψһʵ�����ⲿ���ɵ���������췽��
	
	private static SingleTon singleTon=new SingleTon();//�Լ������Լ���Ψһʵ��
	
	public static SingleTon getInstance(){//���ⲿ�ṩ��һʵ��
		return singleTon;
	}
}

/**
 * �������ģʽ--����ʽ
 * @author CSH
 *
 */
/*public class SingleTon {
	
	private SingleTon(){}//ֻ����һ��Ψһʵ�����ⲿ���ɵ���������췽��
	
	private static SingleTon singleTon=null;//�Լ������Լ���Ψһʵ��
	
	public static synchronized SingleTon getInstance(){//���ⲿ�ṩ��һʵ��,synchronized
		if(singleTon==null){
			singleTon=new SingleTon();
		}
		return singleTon;
	}
}*/
