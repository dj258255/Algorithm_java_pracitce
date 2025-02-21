import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        dfs(1, 2);
        dfs(1, 3);
        dfs(1, 5);
        dfs(1, 7);
        
        System.out.print(sb);
    }
    
    //index: 현재 자리수, num: 현재까지 만든 숫자
    public static void dfs(int index, int num) {
        //모두 소수면
        if(index == N) {
            sb.append(num).append("\n");
            return;
        }
        
        // 1부터 9까지(짝수는 대부분 소수가 아니므로 홀수만 검사)
        for (int i = 1; i <= 9; i += 2) {
            int newNum = num * 10 + i; //자릿수 더하기
            if (isPrime(newNum)) {  //새로운 숫자가 소수이면 계속 탐색
                dfs(index + 1, newNum);
            }
        }
    }
    
    //소수 판별
    public static boolean isPrime(int num) {
        if(num < 2) return false;
        //2부터 제곱근까지 나누어 떨어지는지 확인
        for (int i = 2; i * i <= num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
