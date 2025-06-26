import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer>[] lanes = new Stack[board.length];
        for(int i = 0; i < lanes.length; i++){
            lanes[i] = new Stack<>();
        }
        
        //board를 역순으로 탐색, 각열의 인형을 lanes에 추가
        for(int i = board.length -1; i>= 0; i--){
            for(int j = 0; j< board[i].length; j++){
                if(board[i][j] > 0 ) {
                    lanes[j].push(board[i][j]);
                }
            }
        }
        
        Stack<Integer> bucket = new Stack<>(); //인형을 담을 버켓
        //사라진 인형의 총 개수
        int answer = 0;
        
        //각 열에서 인형을 뽑아 bucket에 추가
        for(int move : moves){
            if (!lanes[move - 1].isEmpty()){ //인형이 있을 때
                int doll = lanes[move - 1].pop();
                //바구니에 인형이 있고, 가장 위에 있는 인형과 같을 때
                if(!bucket.isEmpty() && bucket.peek() == doll){
                    bucket.pop();
                    answer += 2;
                }
                else{//바구니에 인형이 없거나, 가장 위에 있는 인형과 다를 때
                    bucket.push(doll);
                }
            }
        }
        
        return answer;
    }
}