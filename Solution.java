import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int t = 1; t <= T; t++) {
            String N = scan.next();
            for (int len = 1; len <= 10; len++) {
                if (N.startsWith(N.substring(len, len * 2))) {
                    System.out.println("#" + t + " " + len);
                    break;
                }
            }
        }
    }
}
