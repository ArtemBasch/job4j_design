package ru.job4j.inputoutput.argsname;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("Нет значения");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] pairs;
        if (args.length == 0) {
            throw new IllegalArgumentException("Пустой массив");
        }
            for (String val : args) {
                checkFormat(val);
                pairs = val.substring(1).trim().split("=", 2);
                values.put(pairs[0], pairs[1]);
            }
        }

    private void checkFormat(String str) {
        if (str.isBlank()) {
            throw new IllegalArgumentException("Данные отсутствуют");
        }
        if (!str.contains("=")) {
            throw new IllegalArgumentException("Нет знака '='");
        }
        if (str.indexOf("=") == str.length() - 1) {
            throw new IllegalArgumentException("Пара значений не найдена");
        }
        if (str.startsWith("-=")) {
            throw new IllegalArgumentException("Ключ отсутствует");
        }
        if (!str.startsWith("-")) {
           throw new IllegalArgumentException("Строка не начинается с '-'");
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}