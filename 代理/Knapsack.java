package com.java5200.test;

import java.util.Scanner;
//���ݷ�ʵ��01��������
public class Knapsack {
	
	static int c;//��������
	static int n;//��Ʒ����
	static int[] w;//��Ʒi������
	static int[] p;//��Ʒi�ļ�ֵ
	static int cw;
	static int cp;
	static int bestp;

	public static void main(String args[]) {

		Scanner s = new Scanner(System.in);
		// n=Integer.parseInt(s.next());
		// c=Integer.parseInt(s.next());
		
		while (s.hasNext()) {
			
			String line = s.nextLine();// ����c��n
			String[] sArray = line.split(" ");
			n = Integer.parseInt(sArray[0]);
			c = Integer.parseInt(sArray[1]);
			if (n == 0 && c == 0)
				return;
			if (n == 0) {
				System.out.println(0);
				continue;
			}

			line = s.nextLine();// ����w����
			sArray = line.split(" ");
			w = new int[sArray.length];
			for (int j = 0; j < sArray.length; j++) {
				w[j] = Integer.parseInt(sArray[j]);
			}

			line = s.nextLine();// ����p����
			sArray = line.split(" ");
			p = new int[sArray.length];
			for (int j = 0; j < sArray.length; j++) {
				p[j] = Integer.parseInt(sArray[j]);
			}
			System.out.println((Knapsack(p, w, c)));
		}

	}// Main

	///////////////////////////////////////////////////////////////////////////////////////////////
	private static class Element implements Comparable {
		int id;// ��Ʒ���
		double d;

		private Element(int idd, double dd) {
			id = idd;
			d = dd;
		}

		public int CompareTo(Object x) {
			double xd = ((Element) x).d;
			if (d < xd)
				return -1;
			if (d == xd)
				return 0;
			return 1;
		}

		public boolean equals(Object x) {
			return d == ((Element) x).d;
		}

		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	}// Element

	////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////
	public static int Knapsack(int[] pp, int[] ww, int cc) {
		c = cc;
		n = pp.length - 1;
		// n=pp.length;
		cw = 0;
		cp = 0;
		bestp = 0;
		Element[] q = new Element[n + 1];
		// for(int i=1;i<=n;i++)
		// q[i-1]=new Element(i,pp[i]/ww[i]);
		for (int i = 0; i <= n; i++)
			q[i] = new Element(i, pp[i] / ww[i]);
		// ��q[]�Ӵ�С����
		for (int m = 0; m < q.length; m++) {
			for (int j = m + 1; j < q.length; j++) {
				if (q[m].d < q[j].d) {
					// int temp=m;
					// m=j;
					// j=temp;
					int temp1 = q[m].id;
					q[m].id = q[j].id;
					q[j].id = temp1;
					double temp2 = q[m].d;
					q[m].d = q[j].d;
					q[j].d = temp2;
				}
			}
		}

		p = new int[n + 1];
		w = new int[n + 1];

		for (int j = 0; j <= n; j++) {
			p[j] = pp[q[j].id];
			w[j] = ww[q[j].id];
		}

		backtrack(0);
		return bestp;
	}

	static void backtrack(int i) {
		if (i > n) {
			bestp = cp;
			return;
		}
		if (cw + w[i] <= c) {
			cw += w[i];
			cp += p[i];
			backtrack(i + 1);
			cw -= w[i];
			cp -= p[i];
		}
		if (bound(i + 1) > bestp)
			backtrack(i + 1);
	}// backtrack

	private static double bound(int i) {
		double cleft = c - cw;
		double bound = cp;

		while (i <= n && w[i] <= cleft) {
			cleft -= w[i];
			bound += p[i];
			i++;
		}
		if (i <= n)
			bound += p[i] * cleft / w[i];
		return bound;
	}// bound
}
