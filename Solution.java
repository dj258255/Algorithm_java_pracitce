import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String number = scan.next();
        int answer =0;
        for(int i = 0 ; i < number.length() ; i++){
            answer += Integer.parseInt(number.substring(i,i+1));
        }

        System.out.println(answer);
    }
}