package com.toppingoptimizer.optimizing;

import com.toppingoptimizer.toppings.Inventory;
import com.toppingoptimizer.toppings.Topping;
import com.toppingoptimizer.utils.CombinationWorker;
import com.toppingoptimizer.utils.StatType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.toppingoptimizer.utils.Utils.initializeListOf;

public class Optimizer {

    private final Inventory inventory;

    private static final int NUMBER_OF_THREADS = 8;

    public Optimizer(Inventory inventory) {
        this.inventory = inventory;
    }

    public Combination optimize(List<Configuration> configs, List<Topping> toppings) {
        if (configs.size() == 0) {
            throw new IllegalArgumentException("Needs at least one configuration!");
        }

        List<Comparer> threads = new ArrayList<>();
        CombinationWorker generator = new CombinationWorker(toppings.size(), 5);

        long startTime = System.nanoTime();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Comparer c = new Comparer(configs, toppings, generator);
            threads.add(c);
            c.start();
        }

        while (threads.stream().anyMatch(t -> t.getBest() == null)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Error while waiting for threads");
                e.printStackTrace();
            }
        }

        Combination best = null;

        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000.0);
        List<Integer> totals = threads.stream().map(Comparer::getCount).toList();
        int total = 0;
        for (int i : totals) {
            total = total + i;
        }

        int count = configs.size();
        List<Double> maxValues = initializeListOf(count, Double.MIN_VALUE);

        for (Combination c : threads.stream().map(Comparer::getBest).toList()) {
            List<Double> currentValues = initializeListOf(count, 0.0);

            Map<StatType, Double> summary = c.getSummary();

            for (int i = 0; i < count; i++) {
                currentValues.set(i, summary.get(configs.get(i).getStatType()));
            }

            boolean update = true;

            for (int i = 0; i < count; i++) {
                if (maxValues.get(i) > currentValues.get(i)) {
                    update = false;
                    break;
                }
            }

            if (update) {
                maxValues = currentValues;
                best = c;
            }
        }

        return best;
    }

    public Combination optimize(List<Configuration> configs, boolean getUsed) {
        if (configs.size() == 0) {
            throw new IllegalArgumentException("Needs at least one configuration!");
        }

        List<Topping> toppings =
                this.inventory.getToppingType(configs.stream().map(Configuration::getStatType).toList(), getUsed);

        return this.optimize(configs, toppings);
    }

    public List<Combination> optimizeMultiple(List<List<Configuration>> cookies, boolean reset) {
        List<Combination> output = new ArrayList<>();

        List<Topping> toppings = new ArrayList<>(this.inventory.getToppings(reset));
        List<UUID> toppingsUsed = new ArrayList<>();

        for (List<Configuration> cookie : cookies) {
            Combination cookieOutput = optimize(cookie, toppings);
            toppingsUsed.addAll(cookieOutput.getToppingIds());
            toppings = toppings.stream().filter(topping ->
                    toppingsUsed.stream().noneMatch(usedTopping ->
                            usedTopping.equals(topping.getId()))).toList();

            output.add(cookieOutput);
        }

        return output;
    }
}
