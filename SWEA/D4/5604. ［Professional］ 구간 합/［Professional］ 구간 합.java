import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());
            
            long answer = digitSum(B) - (A == 0 ? 0 : digitSum(A - 1));
            
            System.out.println("#" + tc + " " + answer);
        }
    }
    

    static long digitSum(long number) {
        if (number < 10) { // 10미만이면 그만.
            return number * (number + 1) / 2;
        }
        
        //자릿수 구하기
        int n = Long.toString(number).length();
        
        long p = (long) Math.pow(10, n - 1);
        long quotient = number / p; //최고로 높은 자릿수 숫자
        long remainder = number % p; //나머지(보통은 0)
        
        //0부터 p-1까지의 자리 합
        long sum = 45L*(n-1)*(p/10);
//      0 ~ 9 / 45 = 45 * 1
//      0 ~ 99 / 900 = 45 * 20
//      0 ~ 999 / 13500 = 45 * 300
//      0 ~ 9999 / 180000 = 45 * 4000
     
        return quotient * sum
               + (quotient * (quotient - 1) / 2) * p 
               + quotient * (remainder + 1) 
               + digitSum(remainder);
    }
}
