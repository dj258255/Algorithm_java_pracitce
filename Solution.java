import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();

        if (A < B) {
            if(A == 1 && B ==3) {
                System.out.println("A");
            } else
                System.out.println("B");
        }
        else
            System.out.println("A");
    }
}