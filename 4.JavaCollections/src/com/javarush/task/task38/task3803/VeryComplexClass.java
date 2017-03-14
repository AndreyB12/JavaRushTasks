package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.io.IOException;
import java.nio.file.Files;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        try {
            Class c = Class.forName(this.getClass().getCanonicalName());
            Integer i = (Integer) c.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
        }

    }

    public void methodThrowsNullPointerException() {
        try {
            Files.newBufferedReader(null);
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {

    }
}
