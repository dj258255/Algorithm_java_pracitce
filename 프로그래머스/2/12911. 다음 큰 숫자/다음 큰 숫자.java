class Solution {
    public int solution(int n) {
        int count = Integer.bitCount(n);

        while (Integer.bitCount(++n) != count) {
        }

        return n;
    }
}