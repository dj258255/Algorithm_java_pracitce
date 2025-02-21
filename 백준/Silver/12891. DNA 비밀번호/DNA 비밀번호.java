import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int S = Integer.parseInt(st.nextToken());//문자열
        int P = Integer.parseInt(st.nextToken()); //부분문자열
        
        char[] dna = br.readLine().toCharArray(); //DNA 입력
        
        st = new StringTokenizer(br.readLine());
        
        int[] minReq = new int[4];
        for (int i = 0; i < 4; i++) {
            minReq[i] = Integer.parseInt(st.nextToken()); //ACGT 몇개!
        }
        
        
        //몇개씩 있는지 세기
        int[] count = new int[4]; 
        int answer = 0;
        
        for (int i = 0; i < P; i++) {
            addChar(dna[i], count);
        }
        
        //조건에 부합한지 확인.부합하면 증가
        if (isValid(count, minReq)) answer++;
        
        //P부터 S까지 빠르게 가자고
        for (int i = P; i < S; i++) {
            removeChar(dna[i - P], count); //맨 앞 제거
            addChar(dna[i], count); //뒤 추가
            if (isValid(count, minReq)) answer++; //적합함? 적합하면 추가
        }
        
        System.out.println(answer);
    }
    
    static void addChar(char c, int[] count) {
        switch(c) {
            case 'A': count[0]++; break;
            case 'C': count[1]++; break;
            case 'G': count[2]++; break;
            case 'T': count[3]++; break;
        }
    }
    
    static void removeChar(char c, int[] count) {
        switch(c) {
            case 'A': count[0]--; break;
            case 'C': count[1]--; break;
            case 'G': count[2]--; break;
            case 'T': count[3]--; break;
        }
    }
    
    static boolean isValid(int[] count, int[] minReq) {
        for (int i = 0; i < 4; i++) {
            if (count[i] < minReq[i]) return false;
        }
        return true;
    }
}
