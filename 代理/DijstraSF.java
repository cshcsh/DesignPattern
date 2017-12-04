package com.java5200.test;
public class DijstraSF {

	public static void main(String[] args) {
		
		float s=Float.MAX_VALUE-1000;
		float [][]a = {{s,s, s,s, s, s},
				       {s,s,10,s,30,100},
				       {s,10,s,50,s,s},
				       {s,s,50,s,20,10},
				       {s,s, s,20,s,60},
				       {s,100,s,10,60,s}};
		float []dist = new float [7];//dist[]数组记录的是每一个顶点所对应的最短特殊路径长度
		int []L = new int [7];//L[i]记录的是从源到顶点i的最短路径上i的前一个顶点
		int v=1;
		dijkstra(v,a,dist,L);
		
	}
	/**
	 * 解释文档注释如下
	 * Dijkstra算法是解单源最短路径的贪心算法
	 * dist[]数组记录的是每一个顶点所对应的最短特殊路径长度
	 * 从L数组记录的信息找出相应的最短路径
	 * L[i]记录的是从源到顶点i的最短路径上i的前一个顶点
	 * a[][]如下：
	 * s=Float.MAX_VALUE-1000;
	 *   0   1    2    3    4    5 
	 *  ----------------------------
	 * 0 
	 * 1     s    10   s   30   100
	 * 2     10    s  50    s    s
	 * 3     s    50   s   20   10
	 * 4     s     s  20    s   60 
	 * 5    100    s  10   60    s 
	 * */
	/*
	 * @param v
	 * @param a
	 * @param dist
	 * @param L
	 */
	public static void dijkstra(int v,float [][]a,float []dist,int []L){
		//n=6;
		int n=dist.length-1;
		if(v<1||v>n)return;
		boolean []s = new boolean[n+1];
		//初始化
		for(int i =1;i<n;i++){
			dist[i]=a[v][i];
			s[i]=false;
			if(dist[i] == Float.MIN_EXPONENT)L[i]=0;
			else L[i]=v;			
		  }
		dist[v]=0;
		s[v]=true;
		for(int i = 1;i<n;i++){
			float temp = Float.MAX_VALUE;
			int  u = v;
			for(int j=1;j<n;j++){
				if((!s[j])&&(dist[j]<temp)){
					u=j;
					temp=dist[j];
				}//temp=20;
			}
				s[u]=true;
				for(int j=1;j<n;j++)
					if((!s[j])&&(a[u][j]<Float.MAX_VALUE)){
						float newdist = dist[u]+a[u][j];
						if(newdist<dist[j]){
							//dist[j]减少
							dist[j]=newdist;
							L[j]=u;
						}
					}
			}
		for(int i=2;i<=5;i++){
			System.out.println("L["+i+"] = "+L[i]);//L[i]记录的是从源到顶点i的最短路径上i的前一个顶点
		}
		for(int i=2;i<=5;i++){
			System.out.println("dist["+i+"] = "+dist[i]);//dist[]数组记录的是每一个顶点所对应的最短特殊路径长度
		}
	}
}		
