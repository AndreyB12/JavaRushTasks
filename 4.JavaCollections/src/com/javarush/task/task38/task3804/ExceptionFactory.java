package com.javarush.task.task38.task3804;

/**
 * Created by butkoav on 12.03.2017.
 */
public class ExceptionFactory {
    public static Throwable getException(Enum enm) {
        Throwable e;
        if (enm == null) return new IllegalArgumentException();
        String msg = enm.name().toLowerCase().replace('_', ' ');
        msg = msg.substring(0, 1).toUpperCase() + msg.substring(1);
        if (enm.getClass().equals(ExceptionApplicationMessage.class)) {
            e = new Exception(msg);
        } else if (enm.getClass().equals(ExceptionDBMessage.class)) {
            e = new RuntimeException(msg);
        } else if (enm.getClass().equals(ExceptionUserMessage.class)) {
            e = new Error(msg);
        } else e = new IllegalArgumentException();
        return e;
    }
}
