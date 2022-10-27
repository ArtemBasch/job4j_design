package ru.job4j.inputoutput.fileinputstream;

import java.io.*;
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

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
                for (String str : log) {
                    out.println(str);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
    }
}
