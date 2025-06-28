import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer , Integer> statusMap = new HashMap<>();
        Map<Integer , Integer> answerMap = new HashMap<>();
        

        
        for(int i = 0 ; i < records.length; i++){
            //시각 차량번호 내역
            //시각 = HH:MM
            //차량번호 == '0'~'9' 4인 문자열
            //내역은 2 or 3인 IN OR OUT 문자열
            String[] syntax = records[i].split(" ");
            String time = syntax[0];
            int carNum = Integer.parseInt(syntax[1]);
            String status = syntax[2];
            
            int min = timeCal(time); //시간->분
            
            
            if(status.equals("IN")){
                statusMap.put(carNum,min);
                //statusMap.computeIfAbsent(carNum, k -> new ArrayList<>()).add(min);
                //statusMap.get(carNum).add(min);
            } else if(status.equals("OUT")){
                //int tempTime = useTimeCal(statusMap.get(carNum).get(0) , min);
                int outTime = statusMap.remove(carNum);
                answerMap.put(carNum, answerMap.getOrDefault(carNum, 0) + useTimeCal(outTime , min)); //누적 합산
            }
        }
        
        
        
        //아직도 남아있는 In들이 있다면 누적 요금표 계산 ㄱㄱ
        for(Integer num : statusMap.keySet()){
            int finalTime = finalTimeCal(statusMap.get(num));
            answerMap.put(num, answerMap.getOrDefault(num, 0) + finalTime);
        }

        //소요시간
        int basicTime = fees[0];
        int basicCost = fees[1];
        int unitTime = fees[2];
        int unitCost = fees[3];
        
        List<Integer> carNums = new ArrayList<>(answerMap.keySet());
        Collections.sort(carNums);  // 차량번호 오름차순 정렬

        int[] answer = new int[carNums.size()];
        int idx = 0;

        for (int num : carNums) {
            int useTime = answerMap.get(num);
            // 요금 계산 로직
            if (useTime <= basicTime) {
                answer[idx++] = basicCost;
            } else {
                int fee = basicCost;
                useTime -= basicTime;
                fee += ((useTime + unitTime - 1) / unitTime) * unitCost; // 단위시간마다 요금 추가
                answer[idx++] = fee;
            }
        }
        return answer;
    }
    
    
    
    
    public static int useTimeCal(int start,int end){
        return end-start;
    }
    
    public static int finalTimeCal(int min){
        int endTime = 60 * 23 + 59; //23시 59분
        return endTime-min;
    }
    //시간을 분으로 통합
    public static int timeCal(String time){
        String[] baseTime = time.split(":");
        String hours = baseTime[0];
        String min = baseTime[1];
        
        return Integer.parseInt(hours) * 60 + Integer.parseInt(min);
    }
}