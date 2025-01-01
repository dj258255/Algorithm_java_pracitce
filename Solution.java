import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); // N
        int[] numbers = new int[n];

        for(int i = 0; i < numbers.length; i++){
            numbers[i] = scan.nextInt();
        }

        Arrays.sort(numbers);
        System.out.println(numbers[numbers.length/2]);

    }
}