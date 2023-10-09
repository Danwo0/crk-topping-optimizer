package com.toppingoptimizer.optimizing;

import com.toppingoptimizer.toppings.Topping;
import com.toppingoptimizer.utils.CombinationWorker;
import com.toppingoptimizer.utils.StatType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.toppingoptimizer.utils.Utils.initializeListOf;

public class Comparer extends Thread {
    private final List<Configuration> configs;
    private final List<Topping> toppings;
    private final CombinationWorker generator;
    private boolean status;
    private volatile Combination best;
    private int count;

    public Comparer(List<Configuration> configs, List<Topping> toppings, CombinationWorker generator) {
        super();
        this.configs = configs;
        this.toppings = toppings;
        this.generator = generator;
        this.status = false;
        this.best = null;
        this.count = 0;
    }

    public void run() {
        int count = this.configs.size();
        List<Double> maxValues = initializeListOf(count, Double.MIN_VALUE);

        while (generator.hasNext()) {
            int[] next = generator.next();
            if (next == null) continue;

            Combination c = constructCombination(next);
            this.count++;

            List<Double> currentValues = initializeListOf(count, 0.0);

            Map<StatType, Double> summary = c.getSummary();

            for (int i = 0; i < count; i ++) {
                currentValues.set(i, summary.get(this.configs.get(i).getStatType()));
            }

            boolean valid = true;

            for (int i = 0; i < count; i ++) {
                if (!this.configs.get(i).isValid(currentValues.get(i)) || maxValues.get(i) > currentValues.get(i)) {
                    valid = false;
                    break;
                }
            }

            if (!valid) continue;

            maxValues = currentValues;
            this.best = c;
        }
        status = true;
    }

    public Combination getBest() {
        if (status) {
            return best;
        }
        return null;
    }

    public int getCount() {
        return count;
    }

    private Combination constructCombination(int[] indexes) {
        List<Topping> toppings = Arrays.stream(indexes).mapToObj(this.toppings::get).toList();
        return new Combination(toppings);
    }
}
