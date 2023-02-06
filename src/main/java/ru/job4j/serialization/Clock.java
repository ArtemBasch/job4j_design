package ru.job4j.serialization;

public class Clock {
    private final boolean warrantyExpired;
    private final int id;
    private final Characteristics characteristics;

    public Clock(boolean warrantyExpired, int id, Characteristics characteristics) {
        this.warrantyExpired = warrantyExpired;
        this.id = id;
        this.characteristics = characteristics;
    }
}
