package com.epam.trainings.jf.algorithms;

import lombok.experimental.var;

public interface UnionFind<T extends UnionFind<T>> {

    T union(int p, int q);

    boolean isConnected(int p, int q);

    default T union(int... ids) {
        assert ids.length % 2 == 0;
        for (var i = 0; i < ids.length;)
            union(ids[i++], ids[i++]);
        //noinspection unchecked
        return (T) this;
    }
}
