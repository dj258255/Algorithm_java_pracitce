class Solution {
    static char[] words = {'A','E','I','O','U'};
    static String target;
    static int count;
    static int answer;

    public int solution(String word) {
        target = word;
        count = 0;
        answer = 0;
        dfs("", 0);
        return answer;
    }

    private void dfs(String current, int depth) {
        if (depth == 5) return;

        for (char c : words) {
            String next = current + c;
            count++;
            if (next.equals(target)) {
                answer = count;
                return;
            }
            dfs(next, depth + 1);
            if (answer > 0) return;
        }
    }
}
