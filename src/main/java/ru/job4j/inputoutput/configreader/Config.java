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

    public void load() throws IllegalArgumentException {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
          while (in.ready()) {
              String text = in.readLine();
              if ((text != null && !text.startsWith("#") && text.length() > 0)) {
                  String[] str = text.split("=", 2);
                  if (str[0].isEmpty() || str[1].isEmpty() || !text.contains("=")) {
                      throw new IllegalArgumentException();
                  }
                  values.put(str[0], str[1]);
              }
          }
        } catch (Exception e) {
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
