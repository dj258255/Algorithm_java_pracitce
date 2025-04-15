import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] pos = new int[N + 1];
            for (int i = 0; i < N; i++) {
                pos[arr[i]] = i; //pos[번호] = 인덱스
            }
            
            
            int maxLength = 1;
            int curLength = 1;
            
            // 번호 2부터 N까지 순회하며, 이전 번호보다 뒤에 있으면 연속
            for (int i = 2; i <= N; i++) {
                if (pos[i - 1] < pos[i]) { // 앞의 번호가 현재 번호보다 앞쪽에 있다면
                    curLength++;
                } else {
                    curLength = 1;  // 연속이 끊기면 새롭게 시작
                }
                

                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                
            }
            
            int answer = N - maxLength;
            System.out.println("#" + tc + " " + answer);
        }
    }
}
