import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        HashSet<String> employees = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String name = scan.next();
            String action = scan.next();

            if (action.equals("enter")) {
                employees.add(name);
            } else if (action.equals("leave")) {
                employees.remove(name);
            }
        }
        ArrayList<String> result = new ArrayList<>(employees);
        Collections.sort(result, Collections.reverseOrder());

        for (String name : result) {
            System.out.println(name);
        }
    }
}
