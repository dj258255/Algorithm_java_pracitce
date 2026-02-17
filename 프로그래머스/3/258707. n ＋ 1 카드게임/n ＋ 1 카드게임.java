class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;

        // ============================
        // 카드 소유 상태 배열 (인덱스 = 카드번호 - 1)
        // mycards: 초기 손패에 있는 카드
        // newcards: 라운드에서 뽑았지만 아직 코인 안 쓴 카드
        // ============================
        boolean[] mycards = new boolean[n];
        boolean[] newcards = new boolean[n];

        // 초기 손패: 전체의 1/3
        for (int i = 0; i < n / 3; i++) {
            mycards[cards[i] - 1] = true;
        }

        // ============================
        // life: 코인 0으로 낼 수 있는 페어 수 + 코인 1로 만든 페어 수
        //   → 손패끼리 합이 n+1인 페어를 미리 세놓음
        //   → 카드 i와 카드 n+1-i는 짝 (인덱스로는 i와 n-1-i)
        // templife: 뽑은 카드(newcards)끼리 페어가 되는 수
        //   → 코인 2개 써야 사용 가능한 예비 페어
        // ============================
        int life = 0;
        int templife = 0;

        // 초기 손패에서 짝이 되는 페어 수 미리 계산
        // 카드 i+1과 카드 n-i의 합 = n+1 (target)
        for (int i = 0; i < n / 2; i++) {
            if (mycards[i] && mycards[n - i - 1]) {
                life++;
            }
        }

        // ============================
        // 라운드 진행
        // i번째 라운드에서 카드 2장을 뽑고, 페어 1개를 제출해야 생존
        // 최대 라운드 수: n/3 (뽑을 카드 = n - n/3, 라운드당 2장 → (2n/3)/2 = n/3)
        // ============================
        for (int i = 1; i <= n / 3 + 1; i++) {

            // 모든 라운드를 통과했으면 최대 라운드 반환
            if (i == n / 3 + 1) return i;

            // 이번 라운드에 뽑는 카드 2장
            int card1 = cards[n / 3 + 2 * (i - 1)];
            int card2 = cards[n / 3 + 2 * (i - 1) + 1];

            // ============================
            // 뽑은 카드의 짝이 손패에 있으면 → 코인 1개로 페어 완성
            // mycards[n - card] → card의 짝(n+1-card)이 손패에 있는지 확인
            // ============================
            if (mycards[n - card1] && coin > 0) {
                coin--;
                life++;
            }
            if (mycards[n - card2] && coin > 0) {
                coin--;
                life++;
            }

            // ============================
            // 뽑은 카드의 짝이 이전에 뽑은 카드(newcards)에 있으면 → 예비 페어
            // 아직 코인 안 씀, 나중에 필요하면 코인 2개로 사용
            // 짝이 없으면 newcards에 등록해둠
            // ============================
            if (newcards[n - card1]) {
                templife++;
            } else {
                newcards[card1 - 1] = true;
            }

            if (newcards[n - card2]) {
                templife++;
            } else {
                newcards[card2 - 1] = true;
            }

            // ============================
            // life가 0인데 예비 페어가 있고 코인이 2개 이상이면
            // 뽑은 카드끼리 페어를 코인 2개로 사용 (최후의 수단)
            // ============================
            if (life == 0 && coin >= 2 && templife > 0) {
                templife--;
                coin -= 2;
                life++;
            }

            // 페어를 낼 수 없으면 게임 종료
            if (life == 0) return i;

            // 페어 1개 소모하고 다음 라운드로
            life--;
        }

        return -1; // 도달하지 않음
    }
}