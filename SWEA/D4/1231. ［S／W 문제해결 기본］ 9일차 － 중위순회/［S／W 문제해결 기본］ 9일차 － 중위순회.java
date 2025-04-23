import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	static class Node{
		char data;
		Node left;
		Node right;
		
		public Node(char c) {
			this.data = c;
		}
	}
	
	
	public static void search(Node node) {
		if(node == null) return; //리턴
		search(node.left);
		sb.append(node.data);
		search(node.right);
	}
	
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static Node[] nodes;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		
		nodes = new Node[N+1];
		
		for(int i = 1 ; i<= N; i++) {
			nodes[i] = new Node(' ');
		}
		
		for(int i = 0; i < N; i++) {

			
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			nodes[index].data = st.nextToken().charAt(0);
			
			if(st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				nodes[index].left = nodes[a];
			}
			
			if(st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				nodes[index].right = nodes[a];
			}
		}
		

				
		
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t<= 10; t++) {
			
			//입
			input();
			
			//실행
			sb = new StringBuilder();
			search(nodes[1]);
			
			//출
			System.out.println("#" + t + " " + sb.toString());
		}
	}
}