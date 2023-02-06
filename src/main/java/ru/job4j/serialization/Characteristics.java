package ru.job4j.serialization;

import java.util.Arrays;

public class Characteristics {
    private final String manufacturer;
    private final String caliber;
    private final String mechanismType;
    private final boolean waterResistant = false;

    public Characteristics(String manufacturer, String caliber, String mechanismType) {
        this.manufacturer = manufacturer;
        this.caliber = caliber;
        this.mechanismType = mechanismType;

    }

    @Override
    public String toString() {
        return "Characteristics{"
                + "manufacturer=" + manufacturer
                + ", caliber=" + caliber
                + ", mechanism type=" + mechanismType
                + ", water resistant=" + waterResistant
                + '}';
    }
}
