package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        int maxCount = 0;
        if (s == null) return 0;
        if (s.length() == 0) return 0;
        Set<Character> chars;

        for (int i = 0; i < s.length(); i++) {
            chars = new TreeSet<>();
            int k = 0;
            while (!chars.contains(s.charAt(i + k))) {
                chars.add(s.charAt(i + k));
                if (++k > maxCount) maxCount = k;
                if ((i + k) == s.length()) break;
            }

        }

        return maxCount;
    }
}
