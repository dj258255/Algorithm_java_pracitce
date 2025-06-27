class Solution {
    public String solution(String new_id) {
        // 1단계: 대문자를 소문자로
        new_id = new_id.toLowerCase();

        // 2단계: 유효한 문자만 남기기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            if ((c >= 'a' && c <= 'z') || Character.isDigit(c) || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }

        // 3단계: 연속된 마침표(.)를 하나로
        StringBuilder step3 = new StringBuilder();
        boolean dotFlag = false;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '.') {
                if (!dotFlag) {
                    step3.append(c);
                    dotFlag = true;
                }
            } else {
                step3.append(c);
                dotFlag = false;
            }
        }

        // 4단계: 처음이나 끝에 있는 마침표 제거
        String temp = step3.toString();
        if (temp.length() > 0 && temp.charAt(0) == '.') {
            temp = temp.substring(1);
        }
        if (temp.length() > 0 && temp.charAt(temp.length() - 1) == '.') {
            temp = temp.substring(0, temp.length() - 1);
        }

        // 5단계: 빈 문자열이면 "a"
        if (temp.length() == 0) {
            temp = "a";
        }

        // 6단계: 15자 이상이면 자르고, 끝에 마침표 제거
        if (temp.length() >= 16) {
            temp = temp.substring(0, 15);
            if (temp.charAt(temp.length() - 1) == '.') {
                temp = temp.substring(0, temp.length() - 1);
            }
        }

        // 7단계: 길이 2 이하면 마지막 문자 반복
        while (temp.length() < 3) {
            temp += temp.charAt(temp.length() - 1);
        }

        return temp;
    }
}
