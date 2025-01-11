import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for (int t = 1; t <= T; t++) {
            String word = scan.next();
            StringBuilder reverseWord = new StringBuilder(word).reverse();

            if (word.equals(reverseWord.toString())) {
                System.out.println("#" + t + " 1");
            } else {
                System.out.println("#" + t + " 0");
            }
        }
    }
}
