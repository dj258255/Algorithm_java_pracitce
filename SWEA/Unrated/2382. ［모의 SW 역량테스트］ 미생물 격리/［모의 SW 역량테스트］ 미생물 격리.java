import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

public class Solution {
    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    // group : {행, 열, 미생물 수, 이동 방향}
    static int[][] group;
    static int N, M;
    // K는 군집의 개수 (시뮬레이션 진행 중 합쳐지면서 변할 수 있음)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //셀의 개수 (격자 크기)
            M = Integer.parseInt(st.nextToken()); //격리 시간(이동 횟수)
            int K = Integer.parseInt(st.nextToken()); //미생물 군집의 개수
            group = new int[K][4]; //{행, 열, 미생물 수, 이동 방향}
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                group[i][0] = Integer.parseInt(st.nextToken()); //행
                group[i][1] = Integer.parseInt(st.nextToken()); //열
                group[i][2] = Integer.parseInt(st.nextToken()); //미생물 수
                group[i][3] = Integer.parseInt(st.nextToken()); //이동 방향 1~4 상하좌우
            }

            int time = 0;
            //M번 이동
            while (time < M) {
                time++;
                //군집 이동
                for (int i = 0; i < group.length; i++) {
                    //이미 소멸된 군집은 넘어감 (미생물 수 0이면)
                    if (group[i][2] == 0) continue;

                    int d = group[i][3] - 1;
                    int nx = group[i][1] + dx[d]; // 열
                    int ny = group[i][0] + dy[d]; // 행

                    //경계에 도착한 경우
                    if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) {
                        group[i][2] /= 2; //미생물 수 절반 감소 (소수점 이하 버림)
                        //방향 반전
                        if (group[i][2] > 0) {
                            if (group[i][3] == 1) {
                                group[i][3] = 2;
                            } else if (group[i][3] == 2) {
                                group[i][3] = 1;
                            } else if (group[i][3] == 3) {
                                group[i][3] = 4;
                            } else if (group[i][3] == 4) {
                                group[i][3] = 3;
                            }
                        }
                    }
                    //새 위치 업데이트 (미생물 수가 0이어도 이동은 함)
                    group[i][0] = ny;
                    group[i][1] = nx;
                }

                //군집 합체 (한 셀에 여러 군집이 있으면)
                //각 셀별로 합쳐진 미생물 수, 해당 셀에서 최대 미생물 수(합치기 전)와 그 이동 방향을 구함.
                List<int[]> newGroups = new ArrayList<>();
                int[][] total = new int[N][N];
                int[][] max = new int[N][N];
                int[][] newDir = new int[N][N];

                //초기화 (N은 크기이므로 0~N-1)
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        total[i][j] = 0;
                        max[i][j] = 0;
                        newDir[i][j] = 0;
                    }
                }

                //각 군집의 위치에 대해 누적합과, (합치기 전) 미생물 수가 가장 큰 군집의 정보를 기록
                for (int i = 0; i < group.length; i++) {
                    if (group[i][2] > 0) {
                        int r = group[i][0];
                        int c = group[i][1];
                        total[r][c] += group[i][2];
                        if (group[i][2] > max[r][c]) {
                            max[r][c] = group[i][2];
                            newDir[r][c] = group[i][3];
                        }
                    }
                }

                //새 군집 리스트 생성 : 셀에 미생물이 있다면 새 군집으로 추가
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (total[i][j] > 0) {
                            newGroups.add(new int[]{i, j, total[i][j], newDir[i][j]});
                        }
                    }
                }

                //다음 시간 step을 위해 group 배열과 군집 개수 갱신
                group = new int[newGroups.size()][4];
                for (int i = 0; i < newGroups.size(); i++) {
                    group[i] = newGroups.get(i);
                }
            }

            int answer = 0;
            for (int i = 0; i < group.length; i++) {
                answer += group[i][2];
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}
