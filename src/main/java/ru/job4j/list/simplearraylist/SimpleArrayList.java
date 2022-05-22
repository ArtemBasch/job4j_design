package ru.job4j.list.simplearraylist;


import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int defaultLength = 10;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        modCount++;
        size++;

    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
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
            container = Arrays.copyOf(container, defaultLength);
        }
        return container;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
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
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }

}
