import java.util.Scanner;
import java.util.HashSet;

public class Solution {
    static int R, C;
    static char[][] matrix;
    
    static class State {
        int x, y, dir, number;
        
        State(int x, int y, int dir, int number) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.number = number;
        }
        
        @Override
        public int hashCode() {
            return x * 10000 + y * 1000 + dir * 100 + number;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof State)) return false;
            State other = (State) obj;
            return x == other.x && y == other.y && 
                   dir == other.dir && number == other.number;
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        
        for(int t = 1; t <= T; t++) {
            R = scan.nextInt();
            C = scan.nextInt();
            matrix = new char[R][C];
            
            for(int i = 0; i < R; i++) {
                String temp = scan.next();
                for(int j = 0; j < C; j++) {
                    matrix[i][j] = temp.charAt(j);
                }
            }
            
            boolean result = simulate();
            System.out.println("#" + t + " " + (result ? "YES" : "NO"));
        }
    }
    
    public static boolean simulate() {
        HashSet<State> visited = new HashSet<>();
        HashSet<State> toVisit = new HashSet<>();
        
        // 초기 상태 추가
        toVisit.add(new State(0, 0, 2, 0));
        
        while (!toVisit.isEmpty()) {
            // 현재 방문할 상태들을 모두 복사
            HashSet<State> currentStates = new HashSet<>(toVisit);
            toVisit.clear();
            
            for (State current : currentStates) {
                int curX = current.x;
                int curY = current.y;
                int dir = current.dir;
                int number = current.number;
                
                char currentChar = matrix[curY][curX];
                
                // '@' 발견하면 종료
                if (currentChar == '@') {
                    return true;
                }
                
                // '?' 처리
                if (currentChar == '?') {
                    // 4방향 모두 추가
                    for (int newDir = 0; newDir < 4; newDir++) {
                        int nextX = curX;
                        int nextY = curY;
                        
                        if (newDir == 0) nextX = (curX - 1 + C) % C;      // 왼쪽
                        else if (newDir == 1) nextY = (curY - 1 + R) % R; // 위
                        else if (newDir == 2) nextX = (curX + 1) % C;     // 오른쪽
                        else if (newDir == 3) nextY = (curY + 1) % R;     // 아래
                        
                        State nextState = new State(nextX, nextY, newDir, number);
                        if (!visited.contains(nextState)) {
                            toVisit.add(nextState);
                        }
                    }
                    continue;
                }
                
                // 일반 명령어 처리
                int nextDir = dir;
                int nextNumber = number;
                
                switch (currentChar) {
                    case '<': nextDir = 0; break;
                    case '>': nextDir = 2; break;
                    case '^': nextDir = 1; break;
                    case 'v': nextDir = 3; break;
                    case '_': nextDir = (number == 0) ? 2 : 0; break;
                    case '|': nextDir = (number == 0) ? 3 : 1; break;
                    case '+': nextNumber = (number == 15) ? 0 : number + 1; break;
                    case '-': nextNumber = (number == 0) ? 15 : number - 1; break;
                    default:
                        if (currentChar >= '0' && currentChar <= '9') {
                            nextNumber = currentChar - '0';
                        }
                }
                
                // 다음 위치 계산
                int nextX = curX;
                int nextY = curY;
                
                if (nextDir == 0) nextX = (curX - 1 + C) % C;      // 왼쪽
                else if (nextDir == 1) nextY = (curY - 1 + R) % R; // 위
                else if (nextDir == 2) nextX = (curX + 1) % C;     // 오른쪽
                else if (nextDir == 3) nextY = (curY + 1) % R;     // 아래
                
                State nextState = new State(nextX, nextY, nextDir, nextNumber);
                if (!visited.contains(nextState)) {
                    toVisit.add(nextState);
                }
            }
            
            // 방문한 상태들 추가
            visited.addAll(currentStates);
        }
        
        return false;
    }
}