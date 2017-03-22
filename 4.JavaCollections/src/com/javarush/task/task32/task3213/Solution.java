package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {

        if (reader == null) return "";
        StringBuilder sb = new StringBuilder();
        int chr;
        while ((chr = reader.read()) > 0) {
            if (chr == 32) sb.append((char) chr);
            else sb.append((char) (chr + key));
        }
        return sb.toString();
    }

}
