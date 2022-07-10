package ru.job4j.map;

import java.util.*;


public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> mapa = new HashMap<>();
        User us1 = new User("Anton", 2, new GregorianCalendar(2012, 11, 1));
        User us2 = new User("Anton", 2, new GregorianCalendar(2012, 11, 1));
        mapa.put(us1, new Object());
        mapa.put(us2, new Object());

        System.out.println(us1.hashCode());
        System.out.println(us2.hashCode());
        System.out.println(mapa);
    }
}
