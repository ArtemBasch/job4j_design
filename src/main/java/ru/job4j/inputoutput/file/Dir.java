package ru.job4j.inputoutput.file;

import java.io.File;
import java.io.IOException;

public class Dir {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }

        for (File subFile : file.listFiles()) {
            System.out.printf("File name %s \n File size %d \n", subFile.getName(), subFile.length());
        }
    }
}
