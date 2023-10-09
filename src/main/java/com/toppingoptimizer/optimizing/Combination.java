package com.toppingoptimizer.optimizing;

import com.toppingoptimizer.toppings.Topping;
import com.toppingoptimizer.utils.StatType;

import org.apache.commons.math3.util.Precision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.toppingoptimizer.utils.ToppingUtils.getSpecificSynergy;
import static com.toppingoptimizer.utils.ToppingUtils.getToppingValue;


public class Combination {
    private List<Topping> toppings;
    private Map<StatType, Double> summary;

    public Combination(List<Topping> toppings) {
        this.toppings = toppings;
        this.summary = new HashMap<>();

        this.populateSummary();
    }

    public Map<StatType, Double> getSummary() {
        return this.summary;
    }

    public void markUsed(boolean isUsed) {
        for (Topping topping : this.toppings) {
            topping.setIsUsed(isUsed);
        }
    }

    public List<UUID> getToppingIds() {
        return this.toppings.stream().map(Topping::getId).toList();
    }

    private void populateSummary() {
        for(StatType s : StatType.values()) {
            summary.put(s, this.getStatValue(s));
        }
    }

    private double getStatValue(StatType s) {
        double val = 0;

        for (Topping t : this.toppings) {
            val += getToppingValue(t, s);
        }

        val += getSpecificSynergy(this.toppings, s);

        return Precision.round(val, 2);
    }
}
