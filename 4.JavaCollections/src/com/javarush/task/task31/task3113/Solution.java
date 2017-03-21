package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


/* 
Что внутри папки?
*/
public class Solution {
    static int countDir = -1;
    static int countFiles = 0;
    static long totalBytes = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Path root = Paths.get(br.readLine());
        br.close();
        if (!Files.isDirectory(root)) {
            System.out.println(root.toAbsolutePath().toString() + " - не папка");
            return;
        }

        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                countDir++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                countFiles++;
                totalBytes += Files.size(file);
                return FileVisitResult.CONTINUE;
            }
        });

        System.out.println("Всего папок - " + countDir);
        System.out.println("Всего файлов - " + countFiles);
        System.out.println("Общий размер - " + totalBytes);
    }
}
