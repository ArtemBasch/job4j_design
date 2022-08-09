package ru.job4j.set.simpleset;



import ru.job4j.list.simplearraylist.SimpleArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean result = false;
        if (!contains(value)) {
            set.add(value);
            result = true;
        }
        return result;
    }
  
    @Override
    public boolean contains(T value) {
        Iterator<T> iter = set.iterator();
        boolean result = false;
        while (iter.hasNext()) {
            if (Objects.equals(iter.next(), value)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
