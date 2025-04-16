import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	int[] num = new int[N];
        	
        	int highLegnth =0;
        	for(int i = 0 ; i < N; i++) {
        		num[i] = Integer.parseInt(st.nextToken());
        		highLegnth = Math.max(num[i], highLegnth);
        	}
        	
        	
        	int oddDay=0;
        	int evenDay =0;
        	for(int i = 0 ; i < N; i++) {
        		int temp = highLegnth - num[i];
        		evenDay += temp/2; //필요 짝수 날
        		oddDay += temp%2; // 필요 홀 수 날
        	}
        	
        	
        	int left = 0;
        	int right = 12000000;
        	while (left <= right) {
        	    int mid = (left + right) / 2;
        	    
        	    int leftAvail = (mid+1)/2;
        	    int rightAvail = (mid)/2;

        	    long extraOdd = 2 * Math.max(0, evenDay - rightAvail);

        	    if(oddDay + extraOdd <= leftAvail) {
        	    	right = mid-1;
        	    } else {
        	    	left = mid+1;
        	    }
        	}

        	System.out.println("#" + tc + " " + left);

        	
        }
    }
}
