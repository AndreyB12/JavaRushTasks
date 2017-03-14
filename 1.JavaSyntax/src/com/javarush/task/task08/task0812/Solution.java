package com.javarush.task.task08.task0812;

import java.util.ArrayList;
import java.util.Scanner;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        int count = 1;
        int maxCount = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(scanner.nextInt());
            if (i > 0 && list.get(i) == list.get(i - 1)) {
                if (++count > maxCount) maxCount = count;
            } else count = 1;
        }
        System.out.println(maxCount);
    }
}