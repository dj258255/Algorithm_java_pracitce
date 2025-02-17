import java.io.*;
import java.util.*;

public class Solution {
    static int[] dx = {0, 0, 1, 0, -1}; // 멈춤, 상, 우, 하, 좌
    static int[] dy = {0, -1, 0, 1, 0};
    static int[][] BC;
    static int[] userA;
    static int[] userB;
    static int M, A;
    static int userAPosX, userAPosY;
    static int userBPosX, userBPosY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); //총 이동 시간
            A = Integer.parseInt(st.nextToken()); //BC 개수

            userA = new int[M];
            userB = new int[M];

            //사용자 A의 이동 정보
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                userA[i] = Integer.parseInt(st.nextToken());
            }

            //사용자 B의 이동 정보
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                userB[i] = Integer.parseInt(st.nextToken());
            }

            BC = new int[A][4];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                BC[i][0] = Integer.parseInt(st.nextToken()) - 1; // X 좌표
                BC[i][1] = Integer.parseInt(st.nextToken()) - 1; // Y 좌표
                BC[i][2] = Integer.parseInt(st.nextToken()); // 충전 범위
                BC[i][3] = Integer.parseInt(st.nextToken()); // 성능
            }

            // 사용자 초기 위치 설정
            userAPosX = 0;
            userAPosY = 0;
            userBPosX = 9;
            userBPosY = 9;

            int totalCharge = 0;

            // 시간별 이동 및 충전 계산
            for (int t = 0; t <= M; t++) {
                // 현재 시간의 충전량 계산
                totalCharge += getMaxCharge();

                // 이동 (다음 시간 위치로 갱신)
                if (t < M) {
                    userAPosX += dx[userA[t]];
                    userAPosY += dy[userA[t]];
                    userBPosX += dx[userB[t]];
                    userBPosY += dy[userB[t]];
                }
            }

            System.out.println("#" + tc + " " + totalCharge);
        }
    }

    //현재 위치에서 두 사용자 충전량의 최대값 계산
    private static int getMaxCharge() {
        List<Integer> availableA = new ArrayList<>();
        List<Integer> availableB = new ArrayList<>();

        //A가 접속 가능한 BC 찾기
        for (int i = 0; i < A; i++) {
            if (isInRange(userAPosX, userAPosY, BC[i])) {
                availableA.add(i);
            }
        }

        //B가 접속 가능한 BC 찾기
        for (int i = 0; i < A; i++) {
            if (isInRange(userBPosX, userBPosY, BC[i])) {
                availableB.add(i);
            }
        }

        int maxCharge = 0;

        //가능한 모든 BC 조합에 대해 최대 충전량 계산
        for (int a : availableA) {
            for (int b : availableB) {
                int chargeA = BC[a][3];
                int chargeB = BC[b][3];
                if (a == b) { // 같은 BC에 접속하는 경우
                    chargeA /= 2;
                    chargeB /= 2;
                }
                maxCharge = Math.max(maxCharge, chargeA + chargeB);
            }
        }

        //단독 접속의 경우도 포함
        for (int a : availableA) {
            maxCharge = Math.max(maxCharge, BC[a][3]);
        }
        for (int b : availableB) {
            maxCharge = Math.max(maxCharge, BC[b][3]);
        }

        return maxCharge;
    }

    //사용자와 BC의 거리가 충전 범위 이내인지 확인
    private static boolean isInRange(int x, int y, int[] bc) {
        int distance = Math.abs(x - bc[0]) + Math.abs(y - bc[1]);
        return distance <= bc[2];
    }
}