package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }
/*
метод add проверяет существует ли родительский узел.
Если есть родитьель и ребенок - rsl оставляем false.
Если у существующего родителя нет ребенка - добавляем ребенка в
ArrayList прикрепленный к родительской ноде. о_О

 */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentNode = findBy(parent);
        Optional<Node<E>> childNode = findBy(child);
        if (parentNode.isPresent()) {
            if (childNode.isEmpty()) {
                Node<E> newChild = new Node(child);
                parentNode.get().children.add(newChild);
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
