import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        boolean al = true;
        for(int i = 0 ; i <s.length(); i++){
            
            if( i > 0){
                if(s.substring(i-1,i).equals(" ")){
                    al = true;
                }
            }

            
            
            if(al){
                sb.append(s.substring(i,i+1).toUpperCase());
            } else{
                sb.append(s.substring(i,i+1).toLowerCase());
            }
            
            al = false;
        }
        
        
        return sb.toString();
    }
}