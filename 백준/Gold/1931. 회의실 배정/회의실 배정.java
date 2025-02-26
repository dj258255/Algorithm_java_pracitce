import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]> meetings = new ArrayList<>();
        
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new int[]{start, end});
        }
        
        
        //종료 시간이 같으면 시작 시간이 빠른 순으로 정렬
        Collections.sort(meetings, (a, b) -> {
            if(a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        int count = 0;
        int lastEnd = 0;
        for (int[] meeting : meetings) {
            //현재 회의 시작시간이 마지막에 사용한 회의 종료시간보다 크거나 같으면 선택
            if(meeting[0] >= lastEnd) {
                count++;
                lastEnd = meeting[1];
            }
        }
        
        System.out.println(count);
    }
}
