import java.io.*;
import java.util.*;

public class Solution {

    static class Node {
        char data;
        Node left, right;
        Node(char data) { this.data = data; }
    }

    static Node[] nodes;
    static StringBuilder sb;

    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        sb.append(node.data);
        inorder(node.right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
            nodes = new Node[N+1];
            for (int i = 1; i <= N; i++) {
                nodes[i] = new Node(' ');  
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                nodes[idx].data = st.nextToken().charAt(0);
                if (st.hasMoreTokens()) {
                    int l = Integer.parseInt(st.nextToken());
                    nodes[idx].left = nodes[l];
                    if (st.hasMoreTokens()) {
                        int r = Integer.parseInt(st.nextToken());
                        nodes[idx].right = nodes[r];
                    }
                }
            }

            sb = new StringBuilder();
            inorder(nodes[1]);
            System.out.println("#" + tc + " " + sb.toString());
        }
    }
}
