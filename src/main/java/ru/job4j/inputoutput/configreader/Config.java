package ru.job4j.inputoutput.configreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public String checkLine(String str) {
        while (!str.isBlank() && !str.startsWith("#")) {
            return str;
        }
        return null;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String[] words = new String[2];
            String text;
            while (in.ready()) {
                text = in.readLine();
              if (!text.isBlank() && !text.contains("#") && !text.contains("=")) {
                  throw new IllegalArgumentException("Не соответвтсует шаблону 'ключ=значение'");
              }
              if (text.contains("=")) {
                  words = text.split("=", 2);
              }
              if ("".equals(words[0]) || "".equals(words[1])) {
                  throw new IllegalArgumentException("Не соответвтсует шаблону 'ключ=значение'");
              }
              values.put(words[0], words[1]);
            }
    } catch (
    IOException e) {
        e.printStackTrace();
    }
}

    public String value(String key) {
        return values.get(key);
    }
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
