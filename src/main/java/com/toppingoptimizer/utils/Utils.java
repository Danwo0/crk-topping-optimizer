package com.toppingoptimizer.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static <T> List<T> initializeListOf(int size, T defaultVal) {
        List<T> output = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            output.add(defaultVal);
        }

        return output;
    }
}
