import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 0; t < 10; t++) {
            int testCaseNumber = Integer.parseInt(br.readLine().trim());
            int[][] map = new int[100][100];
            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int startPositionX = 0;
            for (int i = 0; i < 100; i++) {
                if (map[99][i] == 2) {
                    startPositionX = i;
                    break;
                }
            }
            
            int currentX = startPositionX;
            int currentY = 99;
            
            while (currentY > 0) {
                if (currentX < 99 && map[currentY][currentX+1] == 1) {
                    while (currentX < 99 && map[currentY][currentX+1] == 1) {
                        currentX++;
                    }
                }
                else if (currentX > 0 && map[currentY][currentX-1] == 1) {
                    while (currentX > 0 && map[currentY][currentX-1] == 1) {
                        currentX--;
                    }
                }
                
                //가로 이동이 없으면 위로 이동
                currentY--;
            }
            
            System.out.println("#" + testCaseNumber + " " + currentX);
        }
    }
}
