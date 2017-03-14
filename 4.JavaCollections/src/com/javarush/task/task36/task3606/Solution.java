package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File path = new File(packageName);

        ClassLoader loader = new ClassLoader() {
            public File file;

            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                Class<?> rslt = null;
                File file = Paths.get(name).toFile();
                String className = file.getName().substring(0, file.getName().length() - 6);
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] data = new byte[fis.available()];
                    fis.read(data);
                    rslt = defineClass(data, 0, data.length);
                } catch (Exception e) {
                }
                return rslt;
            }
        };
        for (File file : path.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".class");
            }
        })) {
            try {
                Class<?> clazz = loader.loadClass(file.getAbsolutePath());
                hiddenClasses.add(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        HiddenClass hc = null;
        for (Class<?> clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase()))
                try {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
        }

        return hc;
    }
}

