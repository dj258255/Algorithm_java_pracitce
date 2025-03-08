import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            B.add(Integer.parseInt(st.nextToken())); //재배열 X
        }


        Collections.sort(A);
        Collections.sort(B);
        Collections.reverse(B);

        int answer = 0;
        for(int i = 0; i < N; i++) {
            answer += A.get(i) * B.get(i);
        }

        //01116
        //87321
        System.out.println(answer);
    }
}
