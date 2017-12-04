package com.java5200.test;

public class tt {  
	  
    private static final double inf = 1e9;  
      
    private int n;                          // 约束个数  3
    private double[][] A;                   // 输入函数参数  
    private double[] b;                    // 约束值  
    private double[] c;                    // 目标函数系数  -12 -15
    private double Z;                       // 目标值  
    private void InitF() {                  // 初始化 First  
    	/*
    	 * 
    	 * max   z= -12x1   -   15x2
		   s.t.    0.25x1   + 0.50x2   +  x3                 =120
		           0.50x1   + 0.50x2            +  x4        =150
		           0.25x1                               +  x5=50
		           
		           xi≥0，i=1,2,…,5

    	 * 
    	 * 
    	 * */
        n = 3; // 约束个数                                 
        A = new double[n+1][n+1];  // 输入函数参数    
        b = new double[n+1];  // 约束值  
        c = new double[n<<1];   // 目标函数系数  
        Z = inf;  
        // 约束初始条件  
        A[1][1] = 0.25;    A[1][2] = 0.50;  
        A[2][1] = 0.50;    A[2][2] = 0.50;  
        A[3][1] = 0.25;    A[3][2] = 0;  
        // 条件值      
        b[1] = 120;  
        b[2] = 150;  
        b[3] = 50;  
        // 目标函数系数  
        c[1] = -12;  c[2] = -15;  
    }  
      
    int m;  
    private double[][] p;  
    private double[][] e, oe;  
    private double[][] E, oE;  
    private double[] X;  
    private boolean[] G;  
    private int[] S;  
    private void InitS() {  
        m = 2;  
        p = new double[n+1][n+m+1];  
        e = new double[n+1][n+1];  
        oe = new double[n+1][n+1];  
        E = new double[n+1][n+1];  
        oE = new double[n+1][n+1];  
        X = new double[n+1];  
        G = new boolean[n+m+1];  
        S = new int[n+1];          
        c = new double[n+m+1];         
        c[1] = -12;  c[2] = -15; c[3] = 0;   c[4] = 0;   c[5] = 0;  
        
        
        /*
    	 * 
    	 * max   z= -12x1   -   15x2
		   s.t.    0.25x1   + 0.50x2   +  x3                 =120
		           0.50x1   + 0.50x2            +  x4        =150
		           0.25x1                               +  x5=50
		           
		           xi≥0，i=1,2,…,5

    	 * 
      	
    	 * 
    	 * */
        
        p[1][1] = 0.25;    p[1][2] = 0.50;    p[1][3] = 1;    p[1][4] = 0;    p[1][5] = 0;  
        p[2][1] = 0.50;    p[2][2] = 0.50;    p[2][3] = 0;    p[2][4] = 1;    p[2][5] = 0;  
        p[3][1] = 0.25;    p[3][2] = 0;       p[3][3] = 0;    p[3][4] = 0;    p[3][5] = 1;  
      
        for(int i = 1; i <= n; i++)  
            for(int j = 1; j <= n; j++)  
                if(i == j)E[i][j] = oE[i][j] = 1;  
                else E[i][j] = oE[i][j] = 0;  
          
        for(int i = 1; i <= n; i++)  
            X[i] = b[i];  
          
        G[1] = false;   G[2] = false;   G[3] = true;    G[4] = true;    G[5] = true;  
          
        S[1] = 3;   S[2] = 4;   S[3] = 5;     
    }  
      
      
    public tt() {  
        InitF();  
        InitS();  
        AlgorithmProcess();  
        solve();  
    }  
      
    private void AlgorithmProcess() {  
        double[] coE = new double[n+1]; 
        double[] r = new double [n+m+1];    
        double[] oEp = new double[n+1]; 
        boolean flag = false;  
        while(true) {  
            for(int i = 1; i <= n; i++){  
                X[i] = 0;  
                for(int j = 1; j <= n; j++)  
                    X[i] += oE[i][j]*b[j];  
            }  
            for(int i = 1; i <= n; i++){  
                coE[i] = 0;  
                for(int j = 1; j <= n; j++)  
                    coE[i] += c[S[j]]*oE[j][i];  
            }  
            int k = -1;  
            flag = false;  
            for(int i = 1; i <= n+m; i++)if(!G[i]){  
                double ans = 0;  
                for(int j = 1; j <= n; j++)  
                    ans += coE[j]*p[j][i];  
                r[i] = c[i] - ans;  
                if(!flag && r[i] < 0){  
                    k = i;  
                    flag = true;  
                }else if(flag && r[i] < r[k]){  
                    k = i;  
                }  
            }  
            if(k == -1)return ;                                  
            int s = -1;  
            flag = false;  
            for(int i = 1; i <= n; i++){  
                oEp[i] = 0;  
                for(int j = 1; j <= n; j++){  
                    oEp[i] += oE[i][j]*p[j][k];  
                }  
                if(oEp[i] > 0){  
                    if(!flag){  
                        s = i;  
                        flag = true;  
                    }else if(flag && X[i]/oEp[i] < X[s]/oEp[s]){  
                        s = i;  
                    }  
                }  
            }  
            if(!flag)return ;                                        
            if(s == -1)return ;                                  
            G[S[s]] = false;    G[k] = true;  
            S[s] = k;  
            System.out.println("k = " + k + "; s = " + s);  
            for(int i = 1; i <= n; i++){  
                p[i][k] = -1.0*oEp[i]/oEp[s];  
                if(i == s)  
                    p[i][k] = 1/oEp[s];  
            }  
            for(int i = 1; i <= n; i++){  
                int id = S[i];  
                for(int j = 1; j <= n; j++){  
                    if(i == s){  
                        e[j][i] = p[j][k];  
                    }else{  
                        if(j == i){  
                            e[j][i] = 1;  
                        }else{  
                            e[j][i] = 0;  
                        }  
                    }  
                }  
            }  
         System.out.println("x3\t" + "x4\t"+"x5\t");  
         for(int i = 1; i <= n; i++){  
              for(int j = 1; j <= n; j++){  
                 System.out.print(oE[i][j] + "\t");  
              }System.out.println();  
          }System.out.println(); 
            for(int i = 1; i <= n; i++){  
                for(int j = 1; j <= n; j++){  
                    oe[i][j] = 0;  
                    for(int t = 1; t <= n; t++){  
                        oe[i][j] += e[i][t]*oE[t][j];  
                    }  
                }  
            }  
            for(int i = 1; i <= n; i++){  
                for(int j = 1; j <= n; j++){  
                    oE[i][j] = oe[i][j];  
                    System.out.print(oE[i][j] + "\t"); 
                }System.out.println();  
            }System.out.println();  
        }  
    }  
    // 最优解输出  
    private void solve() {  
        Z = 0;  
        for(int i = 1; i <= n; i++){  
            int id = S[i];  
            Z += c[id]*X[i];  
            System.out.println(id + "   :\t" + X[i] + "\t*    " + -c[id]);  
        }  
        System.out.println("Z   =\t" + -Z);  
    }  
      
    public static void main(String[] args) {  
        new tt();  
    }  
}  
