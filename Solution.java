import java.util.Scanner;

public class Main {
    static int[][] aftermap;
    static int[][] beforemap;
    static int R, C, T;
    static int airconpos;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        R = scan.nextInt();
        C = scan.nextInt();
        T = scan.nextInt();
        beforemap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                beforemap[i][j] = scan.nextInt();
                if (beforemap[i][j] == -1) {
                    airconpos = i; // 공기청정기 위치 저장
                }
            }
        }

        for (int t = 0; t < T; t++) {
            aftermap = new int[R][C];
            spread(); // 미세먼지 확산
            aircon(); // 공기청정기 작동
            beforemap = aftermap; // 결과를 이전 상태로 복사
        }

        int sum = 2;

        for(int i = 0 ; i < R; i++) {
            for(int j = 0 ; j < C; j++) {
                sum += aftermap[i][j];
            }
        }


        System.out.println(sum);

    }

    // 공기청정기 작동
    public static void aircon() {
        // 위쪽 공기 순환
        for (int i = airconpos; i > 0; i--) {
            aftermap[i][0] = aftermap[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            aftermap[0][i] = aftermap[0][i + 1];
        }
        for (int i = 0; i < airconpos-1; i++) {
            aftermap[i][C - 1] = aftermap[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            aftermap[airconpos-1][i] = aftermap[airconpos-1][i - 1];
        }
        aftermap[airconpos-1][1] = 0;

        // 아래쪽 공기 순환
        for (int i = airconpos + 1; i < R - 1; i++) {
            aftermap[i][0] = aftermap[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            aftermap[R - 1][i] = aftermap[R - 1][i + 1];
        }
        for (int i = R - 1; i > airconpos; i--) {
            aftermap[i][C - 1] = aftermap[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            aftermap[airconpos][i] = aftermap[airconpos][i - 1];
        }
        aftermap[airconpos][1] = 0;

        // 공기청정기 위치 유지
        aftermap[airconpos-1][0] = -1;
        aftermap[airconpos][0] = -1;
    }

    // 미세먼지 확산
    public static void spread() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (beforemap[i][j] > 0) {
                    resolve(i, j);
                }
            }
        }
    }

    public static void resolve(int i, int j) {
        int amount = beforemap[i][j] / 5;
        int spreadCount = 0;

        if (i > 0 && beforemap[i - 1][j] != -1) { // 위쪽
            aftermap[i - 1][j] += amount;
            spreadCount++;
        }
        if (i < R - 1 && beforemap[i + 1][j] != -1) { // 아래쪽
            aftermap[i + 1][j] += amount;
            spreadCount++;
        }
        if (j > 0 && beforemap[i][j - 1] != -1) { // 왼쪽
            aftermap[i][j - 1] += amount;
            spreadCount++;
        }
        if (j < C - 1 && beforemap[i][j + 1] != -1) { // 오른쪽
            aftermap[i][j + 1] += amount;
            spreadCount++;
        }

        aftermap[i][j] += beforemap[i][j] - (amount * spreadCount);
    }

}