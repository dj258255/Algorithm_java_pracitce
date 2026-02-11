import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (String op : operations) {
            String[] temp = op.split(" ");
            int num = Integer.parseInt(temp[1]);

            if (temp[0].equals("I")) {
                map.merge(num, 1, Integer::sum);
            } else if (!map.isEmpty()) {
                int key = num == 1 ? map.lastKey() : map.firstKey();
                if (map.get(key) == 1) map.remove(key);
                else map.merge(key, -1, Integer::sum);
            }
        }

        if (map.isEmpty()) return new int[]{0, 0};
        return new int[]{map.lastKey(), map.firstKey()};
    }
}