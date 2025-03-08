import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] num = new int[N];
        int big = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            num[i] = Integer.parseInt(st.nextToken());
            big = Math.max(big, num[i]); //제일 큰 값 찾기
        }

        int answer = 0;

        for(int i = 1 ; i <= big; i++){
            int sum = 0;

            for(int j = 0; j < N; j++){
                if(num[j] >= i) {
                    sum += i;
                }
            }

            answer = Math.max(answer, sum);
        }

        System.out.println(answer);


    }
}