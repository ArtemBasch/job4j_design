package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;
    private int counter;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public int count() {
        for (int val : data) {
            if (val % 2 == 0) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public boolean hasNext() {


        return counter > 0;
    }

    @Override
    public Integer next() {
        return null;
    }

}
