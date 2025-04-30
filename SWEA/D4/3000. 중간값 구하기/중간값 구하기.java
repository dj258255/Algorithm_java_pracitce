import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N,A;
    static long sum;
    static long answer;
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        
        MedianMaintainer mm = new MedianMaintainer();
        mm.add(A);

        sum = 0;
        answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            mm.add(Integer.parseInt(st.nextToken()));
            mm.add(Integer.parseInt(st.nextToken()));
            sum += mm.getMedian();
        }
        
        answer = sum % 20171109;
        
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= Tc; t++) {
        	input();
        	
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static class MedianMaintainer {
        private PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
        private PriorityQueue<Integer> upper = new PriorityQueue<>();

        public void add(int x) {	
            if (lower.isEmpty() || x <= lower.peek()) {
                lower.offer(x);
            } else {
                upper.offer(x);
            }

            if (lower.size() > upper.size() + 1) {
                upper.offer(lower.poll());
            }
            else if (lower.size() < upper.size()) {
                lower.offer(upper.poll());
            }
        }

        public int getMedian() {
            return lower.peek();
        }
    }
}
