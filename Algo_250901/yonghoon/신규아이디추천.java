import java.util.*;
import java.io.*;
class Solution {
    public String solution(String new_id) {
        String answer = "";
        int len = new_id.length();
       
        new_id = new_id.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (Character.isLetter(new_id.charAt(i))) {
                sb.append(new_id.charAt(i));
                continue;
            } else if (Character.isDigit(new_id.charAt(i))) {
                sb.append(new_id.charAt(i));
                continue;
            } else if (new_id.charAt(i) == '.' || new_id.charAt(i) == '_' || new_id.charAt(i) == '-') {
                sb.append(new_id.charAt(i));   
                continue;
            }
        }
        
        new_id = sb.toString();
        
        sb = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            if (new_id.charAt(i) == '.') {
                sb.append(new_id.charAt(i));
                while (i+1 < new_id.length() && new_id.charAt(i+1) == '.') {
                    i++;
                }
            } else {
                sb.append(new_id.charAt(i));    
            }
        }
        
        new_id = sb.toString();
        
        sb = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            if (i == 0) {
                if (new_id.charAt(i) == '.') {
                    continue;
                }
            } else if (i == new_id.length() -1) {
                if (new_id.charAt(i) == '.') {
                    continue;
                }
            }
            sb.append(new_id.charAt(i));
        }
        new_id = sb.toString();
        
        System.out.println("3" + new_id);
        
        if (new_id.length() == 0) {
            new_id = "a";
        }
        
        sb = new StringBuilder();
        if (new_id.length() >= 16) {
            for (int i = 0; i < 15; i++) {
                if (i == 14 && new_id.charAt(14) == '.') {
                    continue;
                }
                sb.append(new_id.charAt(i));
            }
            new_id = sb.toString();
        }
        
        sb = new StringBuilder();
        if (new_id.length() <= 2) {
            for (int i = 0; i < new_id.length(); i++) {
                sb.append(new_id.charAt(i));
            }
            
            char c = new_id.charAt(new_id.length()-1);
            int len1 = new_id.length();
            while (len1 <= 2) {
                sb.append(c);
                len1++;
            }
            new_id = sb.toString();
        } 
        
        return new_id;
    }
}