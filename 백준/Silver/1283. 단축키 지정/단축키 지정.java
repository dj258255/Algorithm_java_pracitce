import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;  // 오타 수정
	static StringTokenizer st;
	static int N;
	static String[] options;
	static Set<Character> used = new HashSet<>();  // 사용된 단축키 저장

	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		options = new String[N];
		for(int i = 0; i < N; i++) {
			options[i] = br.readLine();
		}
	}

	public static void solve() throws IOException{
		for(int i = 0 ; i < N; i++) {
			String option = options[i];
			String[] words = option.split(" ");
			
			int shortcutIdx = -1;  // 단축키 인덱스 (원본 문자열 기준)
			
			// 1단계: 각 단어의 첫 글자 확인
			int idx = 0;
			for(String word : words) {
				char firstChar = Character.toLowerCase(word.charAt(0));
				if(!used.contains(firstChar)) {
					shortcutIdx = idx;
					used.add(firstChar);
					break;
				}
				idx += word.length() + 1;  // 다음 단어 시작 위치 (공백 포함)
			}
			
			// 2단계: 첫 글자에서 못 찾으면 모든 알파벳 확인
			if(shortcutIdx == -1) {
				for(int j = 0; j < option.length(); j++) {
					char c = option.charAt(j);
					if(c == ' ') continue;
					if(!used.contains(Character.toLowerCase(c))) {
						shortcutIdx = j;
						used.add(Character.toLowerCase(c));
						break;
					}
				}
			}
			
			// 결과 문자열 생성
			if(shortcutIdx == -1) {
				bw.write(option);
			} else {
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < option.length(); j++) {
					if(j == shortcutIdx) {
						sb.append("[").append(option.charAt(j)).append("]");
					} else {
						sb.append(option.charAt(j));
					}
				}
				bw.write(sb.toString());
			}
			bw.newLine();
		}
	}

	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
}