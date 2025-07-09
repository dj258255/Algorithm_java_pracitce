import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        input();
        
        bw.flush();
        bw.close();
        br.close();
    }

    public static class SegmentTree{
        long tree[];
        int treeSize;
        
        public SegmentTree(int size) {
            this.treeSize = size * 4;
            tree = new long[treeSize];
        }
        
        public long init(long[] arr, int node, int start, int end) {
            if(start == end) {
                return tree[node] = arr[start];
            }
            return tree[node] = init(arr, node*2, start, (start+end)/2)
                    + init(arr, node*2+1, (start+end)/2+1, end);
        }
        
        public void update(int node, int start, int end, int idx, long newVal) {
            if(idx < start || idx > end) {
                return;
            }
            
            if(start == end) {
                tree[node] = newVal;
                return;
            }
            
            update(node*2, start, (start+end)/2, idx, newVal);
            update(node*2+1, (start+end)/2+1, end, idx, newVal);
            
            tree[node] = tree[node*2] + tree[node*2+1];
        }
        
        public long sum(int node, int start, int end, int left, int right) {
            if(left > end || right < start) {
                return 0;
            }
            
            if(left <= start && right >= end) {
                return tree[node];
            }
            
            return sum(node*2, start, (start+end)/2, left, right)
                    + sum(node*2+1, (start+end)/2+1, end, left, right);
        }
    }
    
    static int N, M, K;
    
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); //수의 변경이 일어나는 횟수
        K = Integer.parseInt(st.nextToken()); //구간의 합을 구하는 횟수
        
        long arr[] = new long[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(arr, 1, 0, N-1);  //0부터 N-1까지
        
        for(int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            if(a == 1) {
                // b번째 수를 c로 변경 (1-indexed이므로 b-1)
                segmentTree.update(1, 0, N-1, b-1, c);
            } else {
                // b번째 수부터 c번째 수까지의 합 (1-indexed이므로 b-1, c-1)
                long result = segmentTree.sum(1, 0, N-1, b-1, (int)c-1);
                bw.write(result + "\n");
            }
        }
    }
}