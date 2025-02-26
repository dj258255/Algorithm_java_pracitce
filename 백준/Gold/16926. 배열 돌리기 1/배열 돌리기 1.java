import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //회전
        rotateMatrix(matrix, N, M, R);


        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int num : row) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    //각 레이어를 회전
    private static void rotateMatrix(int[][] matrix, int N, int M, int R) {
        int layers = Math.min(N, M) / 2;
        for (int layer = 0; layer < layers; layer++) {
            int top = layer, left = layer, bottom = N - layer - 1, right = M - layer - 1;

            List<Integer> ring = extractRing(matrix, top, left, bottom, right);
            int rotations = R % ring.size();
            List<Integer> rotatedRing = rotateList(ring, rotations);
            insertRing(matrix, top, left, bottom, right, rotatedRing);
        }
    }

    //해당 레이어의 원소들을 리스트에 담음
    private static List<Integer> extractRing(int[][] matrix, int top, int left, int bottom, int right) {
        List<Integer> ring = new ArrayList<>();
        //위쪽 행 (왼쪽 -> 오른쪽)
        for (int j = left; j <= right; j++) {
            ring.add(matrix[top][j]);
        }
        //오른쪽 열 (위 -> 아래, 양 끝 제외)
        for (int i = top + 1; i < bottom; i++) {
            ring.add(matrix[i][right]);
        }
        //아래쪽 행 (오른쪽 -> 왼쪽)
        if (top != bottom) {
            for (int j = right; j >= left; j--) {
                ring.add(matrix[bottom][j]);
            }
        }
        //왼쪽 열 (아래 -> 위)
        if (left != right) {
            for (int i = bottom - 1; i > top; i--) {
                ring.add(matrix[i][left]);
            }
        }
        return ring;
    }

    //리스트를 회전시켜 새 리스트를 생성
    private static List<Integer> rotateList(List<Integer> list, int rotations) {
        int size = list.size();
        List<Integer> rotated = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            rotated.add(list.get((i + rotations) % size));
        }
        return rotated;
    }

    //회전된 리스트의 값을 해당 레이어에 다시 삽입
    private static void insertRing(int[][] matrix, int top, int left, int bottom, int right, List<Integer> ring) {
        int index = 0;
        //위쪽 행
        for (int j = left; j <= right; j++) {
            matrix[top][j] = ring.get(index++);
        }
        //오른쪽 열
        for (int i = top + 1; i < bottom; i++) {
            matrix[i][right] = ring.get(index++);
        }
        //아래쪽 행
        if (top != bottom) {
            for (int j = right; j >= left; j--) {
                matrix[bottom][j] = ring.get(index++);
            }
        }
        //왼쪽 열
        if (left != right) {
            for (int i = bottom - 1; i > top; i--) {
                matrix[i][left] = ring.get(index++);
            }
        }
    }
}
