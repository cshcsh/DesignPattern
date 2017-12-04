package com.java5200.test;

import java.util.Collections;  
import java.util.LinkedList;  
  
/**
 * 带时限的作业排序问题--优先队列式分支限界法  
 * @author CSH
 *
 */
public class WorkSort {  
    public int n;//作业数  
    public int bestc;//最小完成时间和  
    public int [][]m;//个作业所需的处理时间数组  
    public int [][]b;//个作业所需的处理时间排序数组  
    public int[][] a;//数组m和b的对应关系数组  
    public int[] bestx;//最优解  
    public boolean[][] y;//工作数组  
      
    /**
     * 有参构造函数
     * @param n
     * @param m
     */
    public WorkSort(int n,int[][] m){  
        this.n=n;  
        bestc=10000;  
        this.m=m;  
        b=new int[n][2];  
        a=new int[n][2];  
        bestx=new int[n];  
        y=new boolean[n][2];  
    }
    
    /**
     * 
     * @param b
     * @param i
     * @param j
     * @param k
     * @param t
     */
    public void swap(int[][] b,int i,int j,int k,int t){  
        int temp=b[i][j];  
        b[i][j]=b[k][t];  
        b[k][t]=temp;  
    }  
    
    /**
     * 
     * @param x
     * @param i
     * @param j
     */
    public void swap(int[] x,int i,int j){  
        int temp=x[i];  
        x[i]=x[j];  
        x[j]=temp;  
    }  
    
    /**
     * 对个作业在机器1和2上所需时间排序 
     */
    public void sort(){  
        int[] c=new int[n];  
        for(int j=0;j<2;j++){  
            for(int i=0;i<n;i++){  
                b[i][j]=m[i][j];  
                c[i]=i;  
            }  
            for(int i=0;i<n-1;i++){  
                for(int k=n-1;k>i;k--){  
                    if(b[k][j]<b[k-1][j]){  
                        swap(b,k,j,k-1,j);  
                        swap(c,k,k-1);  
                    }  
                }  
            }  
            for(int i=0;i<n;i++)  
                a[c[i]][j]=i;  
        }  
    }  
    
    /**
     * 计算完成时间和下界 
     * @param enode
     * @param f
     * @return
     */
    public int bound(Nodes enode,int[] f){  
        for(int k=0;k<n;k++){  
            for(int j=0;j<2;j++){  
                y[k][j]=false;  
            }  
        }  
        for(int k=0;k<enode.s;k++){  
            for(int j=0;j<2;j++){  
                y[a[enode.x[k]][j]][j]=true;  
            }  
        }  
        f[1]=enode.f[1]+m[enode.x[enode.s]][0];  
        f[2]=((f[1]>enode.f[2])?f[1]:enode.f[2])+m[enode.x[enode.s]][1];  
        int sf2=enode.sf2+f[2];  
        int s1=0;  
        int s2=0;  
        int k1=n-enode.s;  
        int k2=n-enode.s;  
        int f3=f[2];  
        //计算s1的值  
        for(int j=0;j<n;j++){  
            if(!y[j][0]){  
                k1--;  
                if(k1==n-enode.s-1)  
                    f3=(f[2]>f[1]+b[j][0])?f[2]:f[1]+b[j][0];  
                s1+=f[1]+k1*b[j][0];  
            }  
        }  
        //计算s2的值  
        for(int j=0;j<n;j++){  
            if(!y[j][1]){  
                k2--;  
                s1+=b[j][1];  
                s2+=f3+k2*b[j][1];  
            }  
        }  
        //返回完成时间和下界  
        return  sf2+((s1>s2)?s1:s2);  
    }  
    
    /**
     * 优先队列式分支限界法解批处理作业调度问题 
     * @param nn
     * @return
     */
    public int bbFlow(int nn){  
        n=nn;  
        sort();//对个作业在机器1和2上所需时间排序  
        LinkedList<Nodes> heap=new LinkedList<Nodes>();  
        Nodes enode =new Nodes(n);  
        //搜索排列空间树  
        do{  
            if(enode.s==n){  
                //叶节点  
                if(enode.sf2<bestc){  
                    bestc=enode.sf2;  
                    for(int i=0;i<n;i++){  
                        bestx[i]=enode.x[i];  
                    }  
                }  
            }else{  
                //产生当前扩展结点的儿子结点  
                for(int i=enode.s;i<n;i++){  
                    swap(enode.x,enode.s,i);  
                    int[] f=new int[3];  
                    int bb=bound(enode,f);  
                    if(bb<bestc){  
                        //子树可能含有最优解  
                        //结点插入最小堆  
                        Nodes node=new Nodes(enode,f,bb,n);  
                        heap.add(node);  
                        Collections.sort(heap);  
                    }  
                    swap(enode.x,enode.s,i);  
                }//完成结点扩展  
            }  
            //取下一个扩展结点  
            enode=heap.poll();  
        }while(enode!=null&&enode.s<=n);  
        return bestc;  
    }  
  
      
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {  
        int n=3;  
        int[][] m={{2,1},{3,1},{2,3}};//m的下标从0开始  
        WorkSort f=new WorkSort(n,m);  
        f.bbFlow(n);  
        System.out.println("最优批处理作业调度顺序为：");  
        for(int i=0;i<n;i++)  
            System.out.print((f.bestx[i]+1)+" ");  
        System.out.println();  
        System.out.println("最优调度所需的最短时间为："+f.bestc);  
    }  
      
}  

/**
 * Nodes类  实现接口 Comparable
 * @author CSH
 *
 */
class Nodes implements Comparable{  
    int s;//已安排作业数  
    int sf2;//当前机器2上的完成时间和  
    int bb;//当前完成时间和下界  
    int[] f;//f[1]机器1上最后完成时间，f[2]机器2上最后完成时间  
    int[] x;//当前作业调度  
      
    /**
     * 有参构造函数
     * @param n
     */
    public Nodes(int n){  
        //最小堆结点初始化  
        x=new int[n];  
        for(int i=0;i<n;i++)  
            x[i]=i;  
        s=0;  
        f=new int[3];  
        f[1]=0;  
        f[2]=0;  
        sf2=0;  
        bb=0;  
    }  
      
    /**
     * 有参构造函数
     * @param e
     * @param ef
     * @param ebb
     * @param n
     */
    public Nodes(Nodes e,int[] ef,int ebb,int n){  
        //最小堆新结点  
        x=new int[n];  
        for(int i=0;i<n;i++)  
            x[i]=e.x[i];  
        f=ef;  
        sf2=e.sf2+f[2];  
        bb=ebb;  
        s=e.s+1;  
    }  
  
    /**
     * 覆盖compareTo方法
     */
    @Override  
    public int compareTo(Object o) {  
        int xbb=((Nodes) o).bb;  
        if(bb<xbb) return -1;  
        if(bb==xbb) return 0;  
        return 1;  
    }  
}  
/* 
运行结果： 
 
最优批处理作业调度顺序为： 
1 3 2  
最优调度所需的最短时间为：18 
*/  