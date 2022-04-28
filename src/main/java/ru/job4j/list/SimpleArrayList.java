package ru.job4j.list;


import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int pointer;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (pointer >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[pointer++] = value;
        modCount++;

    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = container[Objects.checkIndex(index, container.length)];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size());
        T oldValue = container[index];
        int elemToMove = size - index - 1;
        if (elemToMove > 0) {
            System.arraycopy(container, index + 1, container, index, elemToMove);
            container[--size] = null;
            modCount++;
        }
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        size = container.length;
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < container.length;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }

}
