import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static boolean[] visit = new boolean[10];
    private static String[] sign;
    private static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        sign = new String[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sign[i] = st.nextToken();
        }
        
        for (int i = 0; i < 10; i++) {
            Arrays.fill(visit, false);
            visit[i] = true;
            cycle(i, 0, 0, i + "");
        }
        
        System.out.println(result.get(result.size() - 1));
        System.out.println(result.get(0));
    }

    private static void cycle(int prev, int idx, int cnt, String str) {
        if (cnt == n) {
            result.add(str);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visit[i]) {
                if (sign[idx].equals("<") && prev >= i) {
                    continue;
                }
                if (sign[idx].equals(">") && prev <= i) {
                    continue;
                }
                
                visit[i] = true;
                cycle(i, idx + 1, cnt + 1, str + i);
                visit[i] = false;
            }
        }
    }
}
