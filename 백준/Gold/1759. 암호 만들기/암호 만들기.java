import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main{
	static int L,C;
	static ArrayList<String> list = new ArrayList<>();
	static ArrayList<String> number = new ArrayList<>();
	
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); //서로 다른 L개의 알파벳 소문자들로 구성
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < C; i++) {
			number.add(st.nextToken()); 
		}
		
		Collections.sort(number);
		
		visited = new boolean[C];
		find(0,0);
		
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
	
	
	static public void find(int index,int start) {
		if(index == L) { //글자 수가 L이면
			if(possible()) {
				list.add(sb.toString());
			}
			return;
		}
		
		for(int i = start; i < C; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sb.append(number.get(i));
				find(index+1,i+1);
				sb.deleteCharAt(sb.length()-1);
				visited[i] = false;
			}
		}
		
		return;
	}
	
	
	
	public static boolean possible() {
		int count = 0;
		
		for(int i = 0 ; i < sb.toString().length(); i++) {
			if(sb.toString().charAt(i) =='a' || 
					sb.toString().charAt(i) == 'e' || 
					sb.toString().charAt(i) == 'i' ||  
					sb.toString().charAt(i) == 'o' ||  
					sb.toString().charAt(i) == 'u') {
				count++;
			}
		}
		
		if(count != 0 && sb.toString().length()-count > 1) {
			return true;
		}
		
		
		return false;
	}
}