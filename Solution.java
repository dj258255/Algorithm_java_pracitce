import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    static String number;
    static int n;
    static int maxValue;
    static Set<String> visited;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for (int t = 1; t <= T; t++) {
            number = scan.next();
            n = scan.nextInt();
            maxValue = 0;
            visited = new HashSet<>();

            find(0, number);

            System.out.println("#" + t + " " + maxValue);
        }
    }

    // DFS
    public static void find(int depth, String current) {
        if (depth == n) { // 교환 횟수에 도달한 경우
            maxValue = Math.max(maxValue, Integer.parseInt(current));
            return;
        }

        // 이미 방문한 상태라면 중단 (중복 방지)
        String state = depth + current;
        if (visited.contains(state)) return;
        visited.add(state);
        
        // 모든 가능한 위치 쌍(i, j)에 대해 교환 시도
        for (int i = 0; i < current.length(); i++) {
            for (int j = i + 1; j < current.length(); j++) {
                // 교환 후 새로운 문자열 생성
                String swapped = swap(current, i, j);

                // 다음 단계 탐색
                find(depth + 1, swapped);
            }
        }
    }

    // 두 위치의 숫자를 교환하는 메서드
    public static String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
