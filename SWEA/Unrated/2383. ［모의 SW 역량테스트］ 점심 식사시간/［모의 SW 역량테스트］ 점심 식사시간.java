import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int answer;
    static int people;
    static int steps; //계단 수
    static ArrayList<ArrayList<Integer>> stepsPosition; //계단 위치 ,길이 (x, y, 길이)
    static ArrayList<ArrayList<Integer>> peoplePosition; //사람 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Tc = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= Tc; tc++) {
            stepsPosition = new ArrayList<>(); //초기화
            peoplePosition = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            people = 0;
            steps = 0;
            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (map[i][j] == 1) { //사람이면 사람 위치 저장
                        ArrayList<Integer> tempList = new ArrayList<>();
                        tempList.add(i);
                        tempList.add(j);
                        peoplePosition.add(tempList);
                        people++;
                    }

                    if (map[i][j] > 1) { //계단이면 계단 위치와 길이 저장
                        ArrayList<Integer> tempList = new ArrayList<>();
                        tempList.add(i);
                        tempList.add(j);
                        tempList.add(map[i][j]); //계단 길이
                        stepsPosition.add(tempList);
                        steps++;
                    }
                }
            }

            answer = Integer.MAX_VALUE;
            dfs(0, new int[people], new int[people]);
            System.out.println("#" + tc + " " + answer);
        }
    }

    public static void dfs(int personIdx, int[] peoples, int[] peopleTeam) {
        if (personIdx == people) { //모든 사람 할당 완료
            solve(peoples, peopleTeam);
            return;
        }

        for (int i = 0; i < steps; i++) { //각 계단에 대해 시도
            peopleTeam[personIdx] = i; //personIdx번 사람이 i번째 계단 이용
            //도착 시간
            int distance = Math.abs(peoplePosition.get(personIdx).get(0) - stepsPosition.get(i).get(0))
                         + Math.abs(peoplePosition.get(personIdx).get(1) - stepsPosition.get(i).get(1));
            
            //출발 후 1분 후 도착
            peoples[personIdx] = distance + 1;
            dfs(personIdx + 1, peoples, peopleTeam);
        }
    }

    public static void solve(int[] peoples, int[] peopleTeam) {
        int result = 0;
        //각 계단에 대해 처리
        for (int s = 0; s < steps; s++) {
            ArrayList<Integer> arrivals = new ArrayList<>(); //도착시간들
            for (int i = 0; i < people; i++) {
                if (peopleTeam[i] == s) {  //s번째 계단을 이용하는 사람들의 도착시간 수집
                    arrivals.add(peoples[i]);
                }
            }
            
            
            Collections.sort(arrivals);
            int n = arrivals.size();
            int[] finishTimes = new int[n];
            
            for (int i = 0; i < n; i++) {
                int start;
                int stairLength = stepsPosition.get(s).get(2); //계단 길이
                if (i < 3) { //한 번에 3명까지 가능
                    start = arrivals.get(i);
                } else {
                	//계단 도착하는 시간 , 이미 끝났는지 확인
                    start = Math.max(arrivals.get(i), finishTimes[i-3]);
                }
                finishTimes[i] = start + stairLength;
            }
            
            int stairFinish = 0;
            
            if (n > 0) {
                stairFinish = finishTimes[n-1];
            }
            
            result = Math.max(result, stairFinish);
        }
        
        answer = Math.min(answer, result);
    }
}
