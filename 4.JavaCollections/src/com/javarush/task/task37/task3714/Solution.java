package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    private static Map<Character, Integer> romaNumbs = new TreeMap<>();

    static {
        romaNumbs.put('I', 1);
        romaNumbs.put('V', 5);
        romaNumbs.put('X', 10);
        romaNumbs.put('L', 50);
        romaNumbs.put('C', 100);
        romaNumbs.put('D', 500);
        romaNumbs.put('M', 1000);
    }

    public static int romanToInteger(String s) {
        char[] chars = s.toUpperCase().toCharArray();
        int rslt = 0;
        rslt = romaNumbs.get(chars[chars.length - 1]);
        for (int i = chars.length - 1; i > 0; i--) {
            int curr = romaNumbs.get(chars[i - 1]);
            int prev = romaNumbs.get(chars[i ]);
            if (curr >= prev) rslt += curr;
            else rslt -= curr;
        }
        return rslt;
    }

}
