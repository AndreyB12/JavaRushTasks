package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        boolean finded = false;
        for (int i = 2; i <= 36; i++) {
            try {
                BigInteger nmb = new BigInteger(args[0],i);
                System.out.println(i);
                finded = true;
                break;
            } catch (Exception e) {
            }
        }


        if (!finded) System.out.println("incorrect");
    }
}