package com.epam.trainings.jf.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnionFindTest {

    UnionFind unionFind;

    @BeforeEach
    void setUp() {
        unionFind = new ClassicQuickFind(9);
        unionFind.union(4, 3, 3, 8, 6, 5, 9, 4, 2, 1);
    }

    @Test
    void isConnected() {
        assertFalse(unionFind.isConnected(0, 7));
        assertTrue(unionFind.isConnected(8, 9));
    }

    @Test
    void unionConnected() {
        unionFind.union(8, 9, 5, 0, 7, 2, 6, 1, 1, 0, 6, 7);
        assertTrue(unionFind.isConnected(0, 7));
    }
}