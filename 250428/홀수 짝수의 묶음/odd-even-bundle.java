import java.util.Scanner;

public class Main {
    public static final int MAX_N = 1000;
    
    public static int n;
    public static int[] blocks = new int[MAX_N];
    public static int odd, even;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            blocks[i] = sc.nextInt();
        
        for(int i = 0; i < n; i++) {
            if(blocks[i] % 2 == 0)
                even++;
            else
                odd++;
        }

        int groupNum = 0;
        while(true) {
            if(groupNum % 2 == 0) {
                if(even > 0) {
                    even--;
                    groupNum++;
                }
                else if(odd >= 2) {
                    odd -= 2;
                    groupNum++;
                }
                else {
                    if(even > 0 || odd > 0)
                        groupNum--;
                    break;
                }
            }
            else {
                if(odd > 0) {
                    odd--;
                    groupNum++;
                }
                else {
                    break;
                }
            }
        }

        System.out.print(groupNum);
    }
}