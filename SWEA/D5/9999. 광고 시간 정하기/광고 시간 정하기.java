import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    
    static int L, N;
    static int[][] ads;
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 광고 길이
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 구간 개수
        
        ads = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ads[i][0] = Integer.parseInt(st.nextToken()); // 시작
            ads[i][1] = Integer.parseInt(st.nextToken()); // 끝
        }
        
        // 시작 위치 기준으로 정렬 (필수)
        Arrays.sort(ads, (a,b) -> a[0] - b[0]);
    }
    
    static long best;
    public static void solve() {
        long[] pref = new long[N + 1]; //누적합
        for (int i = 0; i < N; i++) {
            pref[i + 1] = pref[i] + (ads[i][1] - ads[i][0]);
        }

        best = 0;
        // l을 고정하고, ads[l] 구간의 시작부터 L 길이 윈도우 내 커버되는 최대 길이 계산
        for (int l = 0; l < N; l++) {
            int windowStart = ads[l][0];
            int windowEnd = windowStart + L;
   
            // 이분 탐색으로 r 찾기:
            // ads[r][1] > windowEnd 인 첫 인덱스 r (완전히 포함된 구간은 [l..r-1])
            int left = l, right = N;
            while (left < right) {
                int mid = (left + right) / 2 ;
                if (ads[mid][1] <= windowEnd) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            int r = left;
            
            // 완전 포함된 구간 길이
            long total = pref[r] - pref[l];
            
            // r 구간이 부분적으로만 겹칠 때
            if (r < N) {
                int overlap = Math.max(0, Math.min(ads[r][1], windowEnd) - ads[r][0]);
                total += overlap;
            }
            
            best = Math.max(best, total);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            solve();
            bw.write("#" + tc + " " + best);
            bw.newLine();
        }
        
        bw.flush();
        br.close();
    }
}
