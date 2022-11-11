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

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String[] words;
            String text;
            while (in.ready()) {
                text = in.readLine();
                if (text.isBlank() || text.startsWith("#")) {
                    continue;
                }
                if (!text.contains("=")) {
                    throw new IllegalArgumentException("Не соответвтсует шаблону 'ключ=значение'");
                }
                if (text.startsWith("=")) {
                    throw new IllegalArgumentException("Не соответвтсует шаблону 'ключ=значение'");
                }
                if (text.indexOf("=") == text.length() - 1) {
                    throw new IllegalArgumentException("Не соответвтсует шаблону 'ключ=значение'");
                }

            words = text.split("=", 2);
              if (words[0].isEmpty() || words[1].isEmpty()) {
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
        Config config = new Config("app.properties");
        config.load();
        for (String str : config.values.keySet()) {
            String key = str;
            String val = config.value(key);
            System.out.println("Key " + key + " Value " + val);
        }
    }
}
