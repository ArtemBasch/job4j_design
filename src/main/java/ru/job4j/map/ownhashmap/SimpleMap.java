package ru.job4j.map.ownhashmap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int i = indexFor(hash(key), table.length);
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
            if (count >= (LOAD_FACTOR * capacity)) {
                expand();
            }
        }
        return result;
    }

    private int hash(K hashCode) {

        return (hashCode == null) ? 0 : hashCode.hashCode() ^ (hashCode.hashCode() >>> 16);
    }

    private int indexFor(int hash, int length) {
        return (length - 1) & hash;
    }

    private void expand() {
        int newSize = table.length << 1;
        capacity = newSize;
        MapEntry<K, V>[] newTable = new MapEntry[newSize];
        if (table != null) {
            for (MapEntry<K, V> nodes : table) {
                if (nodes != null) {
                    int i = indexFor(hash(nodes.key), newSize);
                    newTable[i] = nodes;
                }
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key), table.length);
        MapEntry<K, V> node = table[i];
        return node != null && hash(key) == hash(node.key) && Objects.equals(key, node.key) ? node.value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int i = indexFor(hash(key), table.length);
        MapEntry<K, V> node = table[i];
        if (node != null) {
            if (key == null && key == node.key) {
                    table[i] = null;
                    modCount++;
                    count--;
                    result = true;
            } else if (hash(key) == hash(node.key) && key.equals(node.key)) {
                table[i] = null;
                modCount++;
                count--;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<K>() {
            private int counter = 0;
            int c = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return counter < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (hasNext() && table[c] == null) {
                    c++;
                }
                K t = table[c++].key;
                counter++;
                return t;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
