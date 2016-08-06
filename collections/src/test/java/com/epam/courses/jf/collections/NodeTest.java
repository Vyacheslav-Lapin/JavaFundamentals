package com.epam.courses.jf.collections;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NodeTest {

    Node<Integer, String> tree;

    @Before
    public void init() {
        tree = new Node<>(8, "Eight");
        tree.put(10, "Ten");
        tree.put(4, "Four");
        tree.put(6, "Six");
        tree.put(5, "Five");
        tree.put(7, "Seven");
        tree.put(2, "Two");
        tree.put(1, "One");
        tree.put(3, "Three");
        tree.put(9, "Nine");
    }

    @Test
    public void putted() {
        assertThat(tree.get(1).get(), is("One"));
        assertThat(tree.get(2).get(), is("Two"));
        assertThat(tree.get(3).get(), is("Three"));
        assertThat(tree.get(4).get(), is("Four"));
        assertThat(tree.get(5).get(), is("Five"));
        assertThat(tree.get(6).get(), is("Six"));
        assertThat(tree.get(7).get(), is("Seven"));
        assertThat(tree.get(8).get(), is("Eight"));
        assertThat(tree.get(9).get(), is("Nine"));
        assertThat(tree.get(10).get(), is("Ten"));
    }

    @Test
    public void mostLeft() {
        assertThat(tree.mostLeft().getKey(), is(1));
    }

    @Test
    public void change() {
        tree.put(4, "For");
        assertThat(tree.get(4).get(), is("For"));
    }

    @Test
    public void removeWithLeftAndRight() throws Exception {
        int size = tree.size();
        tree.remove(4);
        assertThat(tree.getLeft().get().getKey(), is(5));
        assertThat(tree.size(), is(size - 1));
    }
}