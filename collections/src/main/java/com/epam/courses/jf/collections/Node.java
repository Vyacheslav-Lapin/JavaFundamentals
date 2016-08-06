package com.epam.courses.jf.collections;

import com.epam.courses.jf.common.functions.Either;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;
import java.util.function.Consumer;

@Data
@AllArgsConstructor
public class Node<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private Node<K, V> left, right, parent;

    public Node(K key, V value) {
        this(key, value, null, null, null);
    }

    public boolean isRoot() {
        return parent == null;
    }

    void setLeft(K key, V value) {
        left = new Node<>(key, value, null, null, this);
    }

    void setRight(K key, V value) {
        right = new Node<>(key, value, null, null, this);
    }

    Optional<Node<K, V>> getLeft() {
        return Optional.ofNullable(left);
    }

    Optional<Node<K, V>> getRight() {
        return Optional.ofNullable(right);
    }

    Optional<Node<K, V>> getParent() {
        return Optional.ofNullable(parent);
    }

    Either<Node<K, V>, Consumer<V>> find(K key) {
        return key.equals(this.key) ? Either.left(this) :
                this.key.compareTo(key) > 0 ?
                        left == null ? Either.right(value -> setLeft(key, value)) : left.find(key) :
                        right == null ? Either.right(value -> setRight(key, value)) : right.find(key);
    }

    public Optional<V> get(K key) {
        return find(key).mapLeft(Node::getValue).optionalLeft();
    }

    public void put(K key, V value) {
        find(key).apply(
                node -> node.value = value,
                setter -> setter.accept(value));
    }

    Node<K, V> mostLeft() {
        return left == null ? this : left.mostLeft();
    }

    public Optional<V> remove(K key) {
        return find(key).mapLeft(Node::remove).optionalLeft();
    }

    V remove() {
        V oldValue = value;

        getRight()
                .map(Node::mostLeft)
                .map(this::cloneFromAndRemove)
                .orElseGet(() -> getLeft()
                        .map(this::cloneFromAndRemove)
                        .orElseGet(() -> getParent()
                                .map(node -> node.removeChild(this))
                                .orElseGet(() -> {
                                    setKey(null);
                                    setValue(null);
                                    return this;
                                })
                        ));

        return oldValue;
    }

    Node<K, V> cloneFromAndRemove(Node<K, V> target) {
        key = target.key;
        value = target.remove();
        return this;
    }

    Node<K, V> removeChild(Node<K, V> child) {
        if (left == child)
            left = null;
        else if (right == child)
            right = null;

        return this;
    }

    public int size() {
        int result = 1;
        if (getLeft().isPresent()) result += left.size();
        if (getRight().isPresent()) result += right.size();
        return result;
    }
}