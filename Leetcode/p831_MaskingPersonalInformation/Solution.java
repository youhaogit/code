package Leetcode.p831_MaskingPersonalInformation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public String maskPII(String S) {
        if(S == null || S.length() == 0) {
            return S;
        }

        if(S.contains("@")) {
            return maskEmail(S);
        }else {
            return maskPhone(S);
        }
    }

    private String maskEmail(String str) {
        String[] split = str.toLowerCase().split("@");
        String name1 = split[0];
        name1 = name1.substring(0, 1) + "*****" + name1.substring(name1.length() - 1);

        return name1 + split[1];
    }

    private String maskPhone(String str) {
        Set<Character> regex = new HashSet<>(Arrays.asList('+', '-', '(', ')', ' '));

        String phone = "";
        for(char c: str.toCharArray()) {
            if(regex.contains(c)) {
                continue;
            }
            phone += c;
        }

        String lastDigit = phone.substring(phone.length() -4);
        if(phone.length() == 10) {
            return "***-***-" + lastDigit;
        }else if(phone.length() == 11) {
            return "+*-***-***-" + lastDigit;
        }else if(phone.length() == 12) {
            return "+**-***-***-" + lastDigit;
        }else {
            return "+***-***-***-" + lastDigit;
        }
    }
}
