import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{

		input();
		output();
	}
	
	static int Q;
	static Map<String,PriorityQueue<Integer>> map;
	static long answer;
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		Q = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		for(int i = 0 ; i < Q; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int pos = Integer.parseInt(st.nextToken());
			if(pos == 1) {
				String name = st.nextToken();
				int count = Integer.parseInt(st.nextToken());
				
				for(int c = 0; c < count; c++) {//고릴라가 정보를 얻
					map.computeIfAbsent(name, k-> new PriorityQueue<>(
							(a,b) -> b-a
							)).add(Integer.parseInt(st.nextToken()));
				}
				
			} else if(pos==2) {
				//호석이가 정보를 살 때
				String name = st.nextToken();
				int count = Integer.parseInt(st.nextToken()); //가장 비싼 count개 구매 그 고릴라 정보 count개 이하면 모든 정보 구매
				
				int temp = 0;
				while(!map.getOrDefault(name, new PriorityQueue<>()).isEmpty()) {
					if(temp >= count) break;
					answer += map.get(name).poll();
					temp ++;
				}
				
			}
		}
	}

	public static void output() throws IOException{
		bw.write("" + answer);
		bw.flush();
		bw.close();
	}
}