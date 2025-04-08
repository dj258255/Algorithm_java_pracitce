import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			LinkedList<Integer>[] gear = new LinkedList[5];
			for (int i = 1; i <= 4; i++) {
			    gear[i] = new LinkedList<>();
			    st = new StringTokenizer(br.readLine());
			    for (int j = 0; j < 8; j++) {
			        gear[i].add(Integer.parseInt(st.nextToken()));
			    }
			}


			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int number = Integer.parseInt(st.nextToken());
				int rotate = Integer.parseInt(st.nextToken());
				
				int[] check = new int[5]; //톱니바퀴 체크
				check[number] = rotate;
				
				int checkN = rotate;
				for (int j = number + 1; j <= 4; j++) { //오른쪽 체크
					if (gear[j].get(6) != gear[j - 1].get(2)) {
						checkN *= -1;
						check[j] = checkN;
					}
					else break;
				}
				
				checkN = rotate;
				for (int j = number - 1; j >= 1; j--) { //왼쪽 체크
					if (gear[j].get(2) != gear[j + 1].get(6)) {
						checkN *= -1;
						check[j] = checkN;
					}
					else break;
				}
	
				//기어 한번에 돌리기
				for (int j = 1; j <= 4; j++) {
					if (check[j] == 1)
						gear[j].addFirst(gear[j].pollLast());
					if (check[j] == -1)
						gear[j].addLast(gear[j].pollFirst());
				}
			}
			
			int sum = 0;
			for (int i = 1; i <= 4; i++)
				if (gear[i].get(0) == (Object)1)
					sum += 1 * Math.pow(2, i - 1);
			
			System.out.println("#" + tc + " " + sum);
			
			
		}
	}
}