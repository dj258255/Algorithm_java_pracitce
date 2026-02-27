import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int N,C;
	static int[] num;
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		num = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for(int i = 0 ; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	
	static Map<Integer,Integer> orderMap;//순서 
	static Map<Integer,Integer> map; //빈도순 
	public static void solve() throws IOException{
		//빈도 정렬
		map = new HashMap<>();       // 빈도수
		orderMap = new HashMap<>();  // 첫 등장 순서

		for (int i = 0; i < num.length; i++) {
		    map.put(num[i], map.getOrDefault(num[i], 0) + 1);
		    if (!orderMap.containsKey(num[i])) {
		        orderMap.put(num[i], i);
		    }
		}
		
		Integer[] arr = new Integer[num.length];
		for (int i = 0; i < num.length; i++) arr[i] = num[i];

		Arrays.sort(arr, (a, b) -> {
		    int freqDiff = map.get(b) - map.get(a); // 빈도 내림차순
		    if (freqDiff != 0) return freqDiff;
		    return orderMap.get(a) - orderMap.get(b); // 빈도 같으면 먼저 등장한 순
		});
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
		    if (i > 0) sb.append(" ");
		    sb.append(arr[i]);
		}
		bw.write(sb.toString());
		
	}
	
	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}
}