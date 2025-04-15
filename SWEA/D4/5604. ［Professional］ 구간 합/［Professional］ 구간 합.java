import java.io.*;
import java.util.*;

public class Solution {
	static long ans;

	static void calc(long n, long t){
		while(n>0){
			ans+=(n%10)*t;
			n/=10;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++){
			st = new StringTokenizer(br.readLine());
			long A= Long.parseLong(st.nextToken());
			long B= Long.parseLong(st.nextToken());
			ans=0;
			long mul=1;
			while(A<=B){
				while(B%10!=9 && A<=B){
					calc(B,mul);//(21,1)=0+ 1+2=3 //(1,10)=67+ 10=77
					B--;        //(20,1)=3+ 0+2=5 
				}//19 //0
				if(A>B) break; //8>19 //1>0
				while(A%10!=0 && A<=B){
					calc(A,mul);//(8,1)=5+  8=13
					A++;        //(9,1)=13+ 9=22
				}//10
				A/=10;//10->1
				B/=10;//19->1
				long m=(B-A+1)*mul;//(1-1+1)*1=1
				for(int i=0; i<10; i++) ans+=m*i;//22+ 1*0+1*1+1*2+1*3+1*4+1*5+1*6+1*7+1*8+1*9=22+ 45=67
				mul*=10;//1*10=10
			}
			System.out.println("#"+tc+" "+ans);//77
		}
	}	
}
/*
                             A
                             8  9 
    10 11 12 13 14 15 16 17 18 19 
    A                           B
    20 21
        B
                             8+9+    
     +10(1이 10개)
     +45(1~9)    
    +2+0+2+1=77
*/