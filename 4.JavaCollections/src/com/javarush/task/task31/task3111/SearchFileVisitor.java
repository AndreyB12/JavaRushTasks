package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isDirectory(file)) return FileVisitResult.CONTINUE;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName)) {

            return FileVisitResult.CONTINUE;
        }

        if (minSize > 0 && Files.size(file) < minSize) {

            return FileVisitResult.CONTINUE;
        }

        if (maxSize > 0 && Files.size(file) > maxSize) {
            return FileVisitResult.CONTINUE;
        }

        if (partOfContent == null) foundFiles.add(file);
        else {
            byte[] buffer = Files.readAllBytes(file);
            String fileContent = new String(buffer);
            if (fileContent.contains(partOfContent))
                foundFiles.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
