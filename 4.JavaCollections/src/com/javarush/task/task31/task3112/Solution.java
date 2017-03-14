package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }


    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
        Path rsltFile = Paths.get(downloadDirectory + "/" + fileName);
        Path tmpFile = Files.createTempFile(fileName,"tmp");

        InputStream is = url.openStream();
        Files.copy(is, tmpFile);
        is.close();

        return Files.move(tmpFile, rsltFile);
    }
}
