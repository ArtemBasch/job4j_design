package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

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
/*
находит нужный узел по переданому условию.
 */
    public Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> res = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element =  data.poll();
             if (condition.test(element)) {
                 res = Optional.of(element);
                 break;
             }
             for (Node<E> child : element.children) {
                 data.offer(child);
             }
        }
        return res;
    }

    /*
        возвращает узел по значению
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = e -> e.value.equals(value);

        return findByPredicate(predicate);
    }
    /*
    проверяет является ли дерево бинарным.
     */
    public boolean isBinary() {
        Predicate<Node<E>> predicate = e -> e.children.size() > 2;
        return !findByPredicate(predicate).isPresent();
    }
}
