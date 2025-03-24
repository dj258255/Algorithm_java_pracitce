import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        //--------------솔루션 코드를 작성하세요.--------------------------------
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        String[] number = new String[11];
        number[0] = "zero";
        number[1] = "one";
        number[2] = "two";
        number[3] = "three";
        number[4] = "four";
        number[5] = "five";
        number[6] = "six";
        number[7] = "seven";
        number[8] = "eight";
        number[9] = "nine";
        number[10] = "ten";

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = M; i <=N; i++) {
            list.add(i);
        }

        String[] b = new String[list.size()];

        for(int i = 0 ; i < list.size(); i++) {
            String temp = Integer.toString(list.get(i));
            b[i] ="";

            for(int j = 0; j < temp.length(); j++) {
                String a = number[Character.getNumericValue(temp.charAt(j))];
                b[i] = b[i] + a;
            }
        }

        Arrays.sort(b);

        for(int i = 0 ; i < b.length; i++) {
            b[i] = b[i].replaceAll("zero","0")
                    .replaceAll("one", "1")
                    .replaceAll("two", "2")
                    .replaceAll("three", "3")
                    .replaceAll("four", "4")
                    .replaceAll("five", "5")
                    .replaceAll("six", "6")
                    .replaceAll("seven", "7")
                    .replaceAll("eight", "8")
                    .replaceAll("nine", "9");

        }

        int i=0;
        for(int j=0; j < list.size(); j++) {
            if(i==10) {
                System.out.println();
                i=0;
            }
            i++;
            System.out.print(b[j] + " ");
        }



//		for(int i = 0 ; i < answer.length; i++) {
//			System.out.print(answer[i]);
//		}

    }
}
