import java.util.*;

class Solution {
    static Map<String, Integer> memo;

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;

        boolean[] mycards = new boolean[n];
        for (int i = 0; i < n / 3; i++) {
            mycards[cards[i] - 1] = true;
        }

        // 초기 손패끼리 페어 수
        int life = 0;
        for (int i = 0; i < n / 2; i++) {
            if (mycards[i] && mycards[n - i - 1]) life++;
        }

        // 각 라운드에서 뽑는 카드로 생기는 페어 정보를 미리 계산
        // gainLife[i]: i라운드에서 뽑은 카드 중 손패와 짝이 되는 수 (코인1짜리)
        // gainTemp[i]: i라운드에서 뽑은 카드끼리 or 이전 뽑은 카드와 짝이 되는 수 (코인2짜리)
        int rounds = n / 3;
        int[] gainLife = new int[rounds + 1];
        int[] gainTemp = new int[rounds + 1];
        boolean[] newcards = new boolean[n];

        for (int i = 1; i <= rounds; i++) {
            int card1 = cards[n / 3 + 2 * (i - 1)];
            int card2 = cards[n / 3 + 2 * (i - 1) + 1];

            if (mycards[n - card1]) gainLife[i]++;
            if (mycards[n - card2]) gainLife[i]++;

            if (newcards[n - card1]) gainTemp[i]++;
            else newcards[card1 - 1] = true;

            if (newcards[n - card2]) gainTemp[i]++;
            else newcards[card2 - 1] = true;
        }

        // 탑다운 DP: dp(라운드, 코인, life, templife) → 최대 도달 라운드
        memo = new HashMap<>();
        return dp(1, coin, life, 0, gainLife, gainTemp, rounds);
    }

    // 현재 라운드에서 최대 몇 라운드까지 갈 수 있는지
    static int dp(int round, int coin, int life, int templife,
                  int[] gainLife, int[] gainTemp, int maxRound) {

        if (round > maxRound) return round;

        String key = round + "," + coin + "," + life + "," + templife;
        if (memo.containsKey(key)) return memo.get(key);

        // 이번 라운드에서 뽑은 카드 반영
        int newLife = life;
        int newTemp = templife;

        // 뽑은 카드 중 손패와 짝 → 코인 1개씩 써서 life로 전환 가능
        // 선택: 0 ~ gainLife[round]개를 코인 써서 가져감
        int maxGain = Math.min(gainLife[round], coin);
        newTemp += gainTemp[round];

        int best = round; // 이번 라운드에서 끝나는 경우

        // 손패 짝을 몇 개 코인으로 가져갈지 선택
        for (int take = 0; take <= maxGain; take++) {
            int curLife = newLife + take;
            int curCoin = coin - take;
            int curTemp = newTemp;

            // templife를 코인2로 전환할지 선택
            int maxTemp = Math.min(curTemp, curCoin / 2);
            for (int t = 0; t <= maxTemp; t++) {
                int finalLife = curLife + t;
                int finalCoin = curCoin - 2 * t;
                int finalTemp = curTemp - t;

                if (finalLife <= 0) continue;

                // life 1개 소모하고 다음 라운드
                best = Math.max(best,
                    dp(round + 1, finalCoin, finalLife - 1, finalTemp,
                       gainLife, gainTemp, maxRound));
            }
        }

        memo.put(key, best);
        return best;
    }
}