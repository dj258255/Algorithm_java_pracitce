import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int T = 1; T <= 10; T++) {
            int n = sc.nextInt();
            StringBuilder str = new StringBuilder(sc.next());
            boolean hasChanged = true;


            while (hasChanged) {
                hasChanged = false;
                for (int j = 0; j < str.length() - 1; j++) {
                    if (str.charAt(j) == str.charAt(j + 1)) {
                        str.delete(j, j + 2);
                        hasChanged = true;
                        break;
                    }
                }
            }

            System.out.println("#" + T + " " + str);
        }
    }
}
