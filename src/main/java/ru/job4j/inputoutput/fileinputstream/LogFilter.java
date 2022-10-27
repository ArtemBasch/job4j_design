package ru.job4j.inputoutput.fileinputstream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        ArrayList<String> temporary = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            String text;
            while ((text = in.readLine()) != null) {
                temporary.add(text);
            }
           for (String str : temporary) {
               String[] temp = str.split(" ");
               for (String words : temp) {
                   if (words.equals("404")) {
                       res.add(str);
                   }
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  res;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (String str : log) {
            System.out.println(str);

        }

    }
}
