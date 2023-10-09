package com.toppingoptimizer.utils;

import com.google.common.util.concurrent.Monitor;

import org.apache.commons.math3.util.Combinations;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CombinationWorker implements Iterator<int[]> {

    private final Iterator<int[]> combinations;
    private final Monitor mutex;

    public CombinationWorker(int n, int k) {
        this.combinations = new Combinations(n, k).iterator();
        this.mutex = new Monitor();
    }

    public boolean hasNext() {
        return combinations.hasNext();
    }

    @Override
    public int[] next() {
        int[] output = null;
        mutex.enter();

        try {
            if (this.hasNext()) output = this.combinations.next();
        } finally {
            mutex.leave();
        }

        return output;
    }

}
