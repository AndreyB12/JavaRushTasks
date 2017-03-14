package com.javarush.task.task32.task3210;

import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        long number = Long.valueOf(args[1]);
        String text = args[2];
        byte[] bText = new byte[text.getBytes().length];
        try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            raf.seek(number);
            raf.read(bText,0,text.getBytes().length);
            raf.seek(raf.length());
            if (text.equals(new String(bText))) raf.write("true".getBytes());
            else raf.write("false".getBytes());
        } catch (Exception e) {
        }
    }
}
