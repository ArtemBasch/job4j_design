package ru.job4j.list.simplelinkedlist;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    private int modCount;


    @Override
    public void add(E value) {
        linkLast(value);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index < (size / 2)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x.item;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
                }
            return x.item;
        }
    }

    public void linkLast(E value) {

        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;

    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;

        return new Iterator<E>() {
            Node<E> current = first;

            int pointer = 0;

            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> saveCurrent = current;
                current = current.next;
                pointer++;
                return saveCurrent.item;
            }

        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

}