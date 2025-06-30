import java.util.*;

class Solution {
    static class FileInfo {
        String original;
        String head;
        int number;
        String tail;
        int originalIndex;
        
        FileInfo(String original, String head, int number, String tail, int originalIndex) {
            this.original = original;
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.originalIndex = originalIndex;
        }
    }
    
    public String[] solution(String[] files) {
        List<FileInfo> fileInfos = new ArrayList<>();
        
        //파일명 분해
        for(int i = 0; i < files.length; i++) {
            FileInfo info = parseFileName(files[i], i);
            fileInfos.add(info);
        }
        
        // 정렬 규칙에 따라 정렬
        Collections.sort(fileInfos, new Comparator<FileInfo>() {
            
            public int compare(FileInfo f1, FileInfo f2) {
                // 1. HEAD 기준 대소문자 구분 없이 사전순 정렬
                int headCompare = f1.head.toLowerCase().compareTo(f2.head.toLowerCase());
                if (headCompare != 0) {
                    return headCompare;
                }
                
                // 2. HEAD가 같으면 NUMBER 기준 숫자 순 정렬
                int numberCompare = Integer.compare(f1.number, f2.number);
                if (numberCompare != 0) {
                    return numberCompare;
                }
                
                // 3. HEAD와 NUMBER가 모두 같으면 원래 순서 유지 (안정 정렬)
                return Integer.compare(f1.originalIndex, f2.originalIndex);
            }
        });
        
        // 정렬된 결과를 원본 파일명 배열로 변환
        String[] answer = new String[files.length];
        for(int i = 0; i < fileInfos.size(); i++) {
            answer[i] = fileInfos.get(i).original;
        }
        
        return answer;
    }
    
    // 파일명을 HEAD, NUMBER, TAIL로 분해하는 메서드
    private FileInfo parseFileName(String fileName, int originalIndex) {
        StringBuilder headSb = new StringBuilder();
        StringBuilder numberSb = new StringBuilder();
        StringBuilder tailSb = new StringBuilder();
        
        int status = 0; // 0: HEAD, 1: NUMBER, 2: TAIL
        
        for(int i = 0; i < fileName.length(); i++) {
            char c = fileName.charAt(i);
            
            if(status == 0 && Character.isDigit(c)) {
                status = 1;
            }
            else if(status == 1 && !Character.isDigit(c)) {
                status = 2;
            }
            
            if(status == 0) {
                headSb.append(c);
            } else if(status == 1) {
                numberSb.append(c);
            } else {
                tailSb.append(c);
            }
        }
        
        String head = headSb.toString();
        int number = Integer.parseInt(numberSb.toString());
        String tail = tailSb.toString();
        
        return new FileInfo(fileName, head, number, tail, originalIndex);
    }
}
