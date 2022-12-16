package ru.job4j.inputoutput.csvreader;

import ru.job4j.inputoutput.argsname.ArgsName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        CSVReader reader = new CSVReader();
        List<String> lines = new ArrayList<>();
        try(var scanner = new Scanner(new FileInputStream(argsName.get("path"))).useDelimiter("\r\n")) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }
        ArrayList<Integer> pointer = reader.getPointers(lines.get(0), argsName.get("delimiter"), argsName.get("filter"));
        try (PrintWriter out =  new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            for (int i = 0; i < lines.size(); i++) {
                String[] splitLine = lines.get(i).split(argsName.get("delimiter"));
                StringBuffer sb = new StringBuffer();
                for (int k = 0; k < pointer.size(); k++) {
                    sb.append(splitLine[pointer.get(k)]).append((k == pointer.size() - 1) ? System.lineSeparator() : ";");
                }
                out.write(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> getPointers(String headLine, String delimiter, String filter) {
        ArrayList<Integer> result = new ArrayList<>();
        String[] firstLine = headLine.split(delimiter);
        String[] filterKeys = filter.split(",");
        for (int i = 0; i < filterKeys.length; i++ ) {
            for (int j = 0; j < firstLine.length; j++) {
                if (firstLine[j].equals(filterKeys[i])) {
                    result.add(j);
                }
            }
        }
        return result;
    }
}
