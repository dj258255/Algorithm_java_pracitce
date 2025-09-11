import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] x;
	static int[] y;
	static int min;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            //스택이 비지 않았고, 가장 마지막에 들어온 값보다 y가 클 때,
            //건물 하나를 추가해주고, stack에서 pop을 한다.
            //높이가 낮아졌을 때를 의미, 곧 건물이 하나 더 있다는 뜻, pop을 하는 이유는 ?
            while(!stack.isEmpty() && stack.peek() > y)
            {
                answer++;
                stack.pop();
            }
            //높이가 같다면, 같은 빌딩이라는 뜻이기 때문에 stack에 넣을 필요가 없음
            if(!stack.isEmpty() && stack.peek() == y)
            {
                continue;
            }
            //높이가 높아졌다면 stack에 push해서 최고층 높이의 건물을 갱신
            stack.push(y);
        }
        //만약 스택이 비지 않았다면?
        while (!stack.isEmpty())
        {
            //남은 건물이 있다는 뜻이므로 answer를 ++ 해주고, pop 진행
            if(stack.peek() > 0)
            {
                answer++;
            }
            stack.pop();
        }
        System.out.println(answer);
    }
}