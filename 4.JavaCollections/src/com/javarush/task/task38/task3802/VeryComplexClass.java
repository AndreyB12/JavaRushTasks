package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {

        Class<?> act = Class.forName("test.test.NoSuchClass");
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();
    }
}
