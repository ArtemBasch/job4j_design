package ru.job4j.inputoutput.fileoutputstream;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for(int i = 1; i <= 9; i++) {
                for(int j = 1; j <= 9; j++) {
                    out.write((i + " x " + j + " = " + (i*j)).getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
