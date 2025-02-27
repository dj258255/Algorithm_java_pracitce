import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	static int[] month = new int[13]; //12개월 이용 계획
	static int[] tickets = new int[4]; //이용권 가격들 1일 , 1달 ,3달 , 1년
	static boolean[] visited = new boolean[12];
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 4; i++) {
				tickets[i] = Integer.parseInt(st.nextToken()); 
			}
			
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= 12; j++) {
				month[j] = Integer.parseInt(st.nextToken()); 
			}
			
			
			answer = tickets[3]; //최소 1년 가격.
			find(1,0);
			
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	
	public static void find(int index , int sum) {
		if(answer < sum) return;
		if(index > 12) {
			if (answer > sum) {
				answer = sum;
			}
			return;
		}
		
		if(month[index]== 0 ) {
			find(index+1, sum);
		} else {
			//이용권 가격들0: 1일 ,1: 1달 ,2: 3달
			find(index+1, sum + (month[index] * tickets[0]));
			find(index+1, sum+tickets[1]);
			find(index+3,  sum+tickets[2]);
		}
	}
}