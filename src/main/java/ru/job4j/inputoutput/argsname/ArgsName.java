package ru.job4j.inputoutput.argsname;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    /*
    возвращает значение из карты values по ключу
     */
    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("Нет значения");
        }
        return values.get(key);
    }

    /*
    помещает пары в карту values для дальнейшего использования.
     */

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

        /*
        проверяет аргументы на соответствие шаблону "-ключ=значение"
         */

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
           throw new IllegalArgumentException("Нет знака '-'");
        }
    }
        /*
        передает аругменты в виде массива на преобразование в пары.
         */
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