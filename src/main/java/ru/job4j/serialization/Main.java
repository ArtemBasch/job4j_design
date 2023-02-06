package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact(11111, "9065676637"),
                new String[] {"Worker", "Married"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(person));
        System.out.println("__________________");

        /* Модифицируем json-строку */
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);

        System.out.println(personMod);
        System.out.println("_____");
        System.out.println(gson);
    }
}