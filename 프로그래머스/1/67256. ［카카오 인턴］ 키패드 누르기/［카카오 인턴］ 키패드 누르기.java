
class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        int firstLeftHand = 10; //* 시작
        int firstRightHand = 12; //#시작
        int secondHand = 0;
        
        for(int i =0 ; i < numbers.length; i++){
            
            if(
                numbers[i] == 1 ||
                numbers[i] == 4 ||
                numbers[i] == 7
              ){
                sb.append("L");
                firstLeftHand = numbers[i];
            } else if(
                numbers[i] == 3 ||
                numbers[i] == 6 ||
                numbers[i] == 9
            ){
                sb.append("R");
                firstRightHand = numbers[i];
            } else {
                
                if(numbers[i] == 0){
                    secondHand = 11;
                } else{
                    secondHand = numbers[i];
                }
                //0 매핑시켜야함.
                
                
                int leftDistance = cal(firstLeftHand,secondHand);
                int rightDistance = cal(firstRightHand,secondHand);
                
                if(leftDistance > rightDistance){ //왼손이 더 멀 때
                    sb.append("R");
                    firstRightHand = secondHand;
                } else if(rightDistance > leftDistance){ //오른손이 더 멀 때
                    sb.append("L");
                    firstLeftHand = secondHand;
                } else if(leftDistance==rightDistance){ //둘이 거리가 같을 때
                    if(hand.equals("right")){
                        sb.append("R");
                        firstRightHand = secondHand;
                    } else if(hand.equals("left")){
                        sb.append("L");
                        firstLeftHand = secondHand;
                    }
                }
            }
        }
        
        String answer = sb.toString();
        return answer;
    }
    
    
    public static int cal(int first, int second){
        int distance = Math.abs(first-second);
        int count = 0;
        while(distance >= 1){
            if(distance >= 3){
                distance -=3;
                count++;
            } else{
                distance -=1;
                count++;
            }
        }
        
        return count;
    }
}