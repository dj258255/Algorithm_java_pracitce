import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			
			
			Queue<Integer> queue = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			
			int i = 1;
			while(true) {
				int node = queue.poll();
				node -= i;
				if(node <= 0) {
					queue.add(0);
					break;
				} else {
					queue.add(node);
				}
				i = (i==5) ? 1 : i+1;
			}
			
            System.out.print("#" + tc + " ");
            for (int num : queue) {
                System.out.print(num + " ");
            }
            System.out.println();
		}
	}
}