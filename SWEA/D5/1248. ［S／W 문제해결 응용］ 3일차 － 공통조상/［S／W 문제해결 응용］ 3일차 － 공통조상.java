import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static class Node{
		int number; //그 노드의 번호
		Node root;
		Node left;
		Node right;
		
		public Node(int number) {
			this.number = number;
		}
	}
	
	
	
	static Node[] nodes;
	static int V,E;
	static int searchN1,searchN2;
	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		searchN1 = Integer.parseInt(st.nextToken());
		searchN2 = Integer.parseInt(st.nextToken());
		
		nodes = new Node[V+1];
		
		st = new StringTokenizer(br.readLine().trim());
		for(int i = 0 ; i < E; i++) {
			int a = Integer.parseInt(st.nextToken()); //부모
			int b = Integer.parseInt(st.nextToken()); //자식
			create(a,b); //트리 생성
		}
		
		count = 0;
		search1List = new ArrayList<>();
		search2List = new ArrayList<>();
	}
	
	public static void create(int a, int b) {
		if(nodes[a]== null ) {
			nodes[a] = new Node(a);  //만약 널이면 새로 추가
		}
		if(nodes[b]== null ) {
			nodes[b] = new Node(b); //만약 널이면 새로 추가
		}
		
		
		nodes[b].root = nodes[a]; //자식 부모 연결
		//부모 자식 연결
		if (nodes[a].left == null) {
		    nodes[a].left = nodes[b];
		} else {
		    nodes[a].right = nodes[b];
		}

		


	}

	
			
	static List<Integer> search1List;
	static List<Integer> search2List; 
	public static void createList(Node node,  List<Integer> list ) {
		if(node == null) return;
		list.add(node.number);
		createList(node.root , list);
	}
	
	

    //리스트 뒤집고, 앞에서부터 비교
    public static int findRoot() {
        Collections.reverse(search1List);
        Collections.reverse(search2List);
        
        
        int unionroot = 0;
        int max = Math.min(search1List.size(), search2List.size());
        for (int i = 0; i < max; i++) {
            if (search1List.get(i).equals(search2List.get(i))) {
                unionroot = search1List.get(i);
            } else {
                break;
            }
        }
        return unionroot;
    }
	
	static int count;
	public static void unionRootSolve(Node node) {
		if(node == null) return;
		count++;
		unionRootSolve(node.left);
		unionRootSolve(node.right);
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int Tc= Integer.parseInt(br.readLine().trim());
		
		for(int t= 1; t <= Tc; t++) {
			//입력
			input();
			//리스트 만들기
			createList(nodes[searchN1], search1List);
			createList(nodes[searchN2], search2List );
			//공통조상 찾기
			int unionRoot = findRoot();
			//임시출력
			//System.out.println(search1List.toString());
			//System.out.println(search2List.toString());
			//System.out.println(unionRoot);
			
			//공통조상의 서브트리 찾기
			unionRootSolve(nodes[unionRoot]);
			
			sb.append("#").append(t).append(" ").append(unionRoot).append(" ").append(count).append("\n");
			//출력
			//System.out.println(t);
			//System.out.print("");
		}
		System.out.println(sb.toString());
	}
}