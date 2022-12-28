package ru.job4j.inputoutput.csvreader;

import ru.job4j.inputoutput.argsname.ArgsName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static final String DELIMITER = ";";
    private static final String CONSOLE_OUT = "stdout";
    /*
    Добавляет содержимое исходного файла в коллекцию lines,
    проходит по ней циклом for и вносит запрошенные (по соответствующим ключам, переданным в строке filter) данные в новый файл.
    При получении в параметрах ключа stdout выводит искомые строки в консоль.
     */
    public static void handle(ArgsName argsName) throws Exception {
        CSVReader reader = new CSVReader();
        List<String> lines = new ArrayList<>();
        try (var scanner = new Scanner(new FileInputStream(argsName.get("path"))).useDelimiter("\r\n")) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> pointer = reader.getPointers(lines.get(0), argsName.get("delimiter"), argsName.get("filter"));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lines.size(); i++) {
                String[] splitLine = lines.get(i).split(argsName.get("delimiter"));
                for (int k = 0; k < pointer.size(); k++) {
                    sb.append(splitLine[pointer.get(k)]).append((k == pointer.size() - 1) ? System.lineSeparator() : ";");
                }
            }
        if (CONSOLE_OUT.equals(argsName.get("out"))) {
            System.out.println(sb);
        } else {
            try (PrintWriter out = new PrintWriter(new FileOutputStream(argsName.get("out")))) {
                out.write(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        /*
        возвращает коллекцию индексов соответствующих фильтрам.
         */
    private static ArrayList<Integer> getPointers(String headLine, String delimiter, String filter) {
        ArrayList<Integer> result = new ArrayList<>();
        String[] firstLine = headLine.split(delimiter);
        String[] filterKeys = filter.split(",");
        for (int i = 0; i < filterKeys.length; i++) {
            for (int j = 0; j < firstLine.length; j++) {
                if (firstLine[j].equals(filterKeys[i])) {
                    result.add(j);
                }
            }
        }
        return result;
    }
        /*
        метод проверяет аргументы:
        количество не меньше 4, расширение файла 'csv', разделитель всегда ';',
         */
    private static void checkArgs(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist!");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("The file is not a 'csv'");
        }
        if (!DELIMITER.equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        handle(argsName);
    }
}
