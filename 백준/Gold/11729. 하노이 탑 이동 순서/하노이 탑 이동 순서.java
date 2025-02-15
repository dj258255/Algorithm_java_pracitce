import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int result = hanoi(n, 1, 2, 3);

        System.out.println(result);
        System.out.println(sb);
    }

    public static int hanoi(int n, int from, int temp, int to){
        if(n == 0){
            return 0;
        }

        int count = 0;

        count += hanoi(n-1, from, to , temp); //1번 기둥 원판 1개를 2번기둥으로

        sb.append(from + " " + to + "\n");

        count++;
        count += hanoi(n-1, temp, from, to);

        return count;
    }
}