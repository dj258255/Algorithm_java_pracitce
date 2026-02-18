class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            int merged = arr1[i] | arr2[i];

            // n자리 이진수 문자열로 변환 (앞자리 0 패딩)
            String binary = String.format("%" + n + "s",
                    Integer.toBinaryString(merged)).replace(' ', '0');

            // 1 → #, 0 → 공백
            answer[i] = binary.replace('1', '#').replace('0', ' ');
        }

        return answer;
    }
}