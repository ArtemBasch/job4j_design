package ru.job4j.inputoutput.csvreader;

import ru.job4j.inputoutput.argsname.ArgsName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

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
        }
        ArrayList<Integer> pointer = reader.getPointers(lines.get(0), argsName.get("delimiter"), argsName.get("filter"));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lines.size(); i++) {
                String[] splitLine = lines.get(i).split(argsName.get("delimiter"));
                for (int k = 0; k < pointer.size(); k++) {
                    sb.append(splitLine[pointer.get(k)]).append((k == pointer.size() - 1) ? System.lineSeparator() : ";");
                }
            }
        if (argsName.get("out").equals("stdout")) {
            System.out.println(sb);
        } else {
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
                out.write(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        /*
        возвращает коллекцию индексов соответствующих фильтрам.
         */
    private ArrayList<Integer> getPointers(String headLine, String delimiter, String filter) {
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
    private void checkArgs(String[] args, ArgsName argsName) {
        File file = new File(argsName.get("path"));
        if (args.length < 4) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("Is not a file");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("The file is not a 'csv'");
        }
        if (!argsName.get("delimiter").equals(";")) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
    }

    public static void main(String[] args) throws Exception {
        CSVReader cr = new CSVReader();
        File file = new File(args[0]);
        File target = new File(args[1]);
        String[] arguments = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=name,position,phone"};
        ArgsName argsName = ArgsName.of(arguments);
        cr.checkArgs(arguments, argsName);
        handle(argsName);
    }
}
