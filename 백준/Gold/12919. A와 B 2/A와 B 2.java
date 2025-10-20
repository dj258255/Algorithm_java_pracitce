import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static String S, T;
    static int answer;

    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        S = br.readLine();
        T = br.readLine();
    }

    public static int solve(String current){
        if(current.length() == S.length()){
            return current.equals(S) ? 1 : 0;
        }

        int result = 0;

        if(current.charAt(current.length() - 1) == 'A'){
            result = Math.max(result, solve(current.substring(0, current.length() - 1)));
        }

        if(current.charAt(0) == 'B'){
            String reversed = new StringBuilder(current.substring(1)).reverse().toString();
            result = Math.max(result, solve(reversed));
        }

        return result;
    }

    public static void output() throws IOException{
        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        input();
        answer = solve(T);
        output();
    }
}