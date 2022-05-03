package ru.job4j.list;


import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    final private int DEFAULT_LENGTH = 10;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
            container[size] = value;
        size++;

    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T oldValue = container[index];
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

    public T[] grow() {
        if (container.length > 0) {
            container = Arrays.copyOf(container, container.length * 2);
        } else {
            container = Arrays.copyOf(container, DEFAULT_LENGTH);
        }
        return container;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {

        int expectedModCount = modCount;
        return new Iterator<T>() {
            int pointer = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }
            @Override
            public T next() {
                if (!hasNext() || container[pointer] == null) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }

}
