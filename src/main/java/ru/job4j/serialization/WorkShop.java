package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorkShop {
    public static void main(String[] args) {
        final Clock clock = new Clock(false, 234581, new Characteristics("Orient", "f76", "mechanical"));
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String clockGson = gson.toJson(clock);
        System.out.println(clockGson);
        final Clock clockFromGson = gson.fromJson(clockGson, Clock.class);
    }
}
