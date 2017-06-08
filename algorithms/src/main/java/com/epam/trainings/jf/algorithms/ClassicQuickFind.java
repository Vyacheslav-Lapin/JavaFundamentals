package com.epam.trainings.jf.algorithms;

import lombok.experimental.var;
import lombok.val;

@SuppressWarnings("WeakerAccess")
public class ClassicQuickFind implements UnionFind<ClassicQuickFind> {

    private final int[] ids;

    public ClassicQuickFind(final int quantity) {
        ids = new int[quantity + 1];
        for (int i = 0; i < ids.length; i++)
            ids[i] = i;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return ids[p] == ids[q];
    }

    @Override
    public ClassicQuickFind union(int p, int q) {
        assert p < ids.length;
        assert q < ids.length;
        if (!isConnected(p, q)) {
            val pId = ids[p];
            val qId = ids[q];

            for (var i = 0; i < ids.length; i++)
                if (ids[i] == pId)
                    ids[i] = qId;
        }
        return this;
    }
}
