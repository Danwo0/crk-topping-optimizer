package com.toppingoptimizer.utils;

import com.toppingoptimizer.toppings.SubStat;
import com.toppingoptimizer.toppings.Topping;

import java.util.List;

import static com.toppingoptimizer.utils.Utils.initializeListOf;

public class ToppingUtils {

    public static double getToppingValue(Topping t, StatType s) {
        double val = 0;

        if (t.getMainType() == s) {
            val += t.getValue();
        }

        for (SubStat sub : t.getSubStats()) {
            if (sub.getType() == s) {
                val += sub.getValue();
                return val;
            }
        }

        return val;
    }

    public static double getSpecificSynergy(List<Topping> toppings, StatType s) {
        List<Double> synergies = getSynergies(toppings);
        return synergies.get(s.ordinal());
    }

    private static List<Double> getSynergies(List<Topping> toppings) {
        List<Integer> count = initializeListOf(StatType.values().length, 0);

        for (Topping t : toppings) {
            count.set(t.getMainType().ordinal(), count.get(t.getMainType().ordinal()) + 1);
        }

        List<Double> synergy = initializeListOf(StatType.values().length, 0.0);

        if (count.get(StatType.ATTACK.ordinal()) == 5) {
            synergy.set(StatType.ATTACK.ordinal(), 5.0);
        } else if (count.get(StatType.ATTACK.ordinal()) >= 3) {
            synergy.set(StatType.ATTACK.ordinal(), 3.0);
        }

        if (count.get(StatType.COOLDOWN.ordinal()) == 5) {
            synergy.set(StatType.COOLDOWN.ordinal(), 5.0);
        }

        if (count.get(StatType.CRIT_RATE.ordinal()) == 5) {
            synergy.set(StatType.CRIT_RATE.ordinal(), 5.0);
        }

        if (count.get(StatType.DAMAGE_REDUCTION.ordinal()) == 5) {
            synergy.set(StatType.DAMAGE_REDUCTION.ordinal(), 5.0);
        }

        if (count.get(StatType.ATTACK_SPEED.ordinal()) == 5) {
            synergy.set(StatType.ATTACK_SPEED.ordinal(), 2.0);
        } else if (count.get(StatType.ATTACK_SPEED.ordinal()) >= 3) {
            synergy.set(StatType.ATTACK_SPEED.ordinal(), 1.0);
        }

        return synergy;
    }
}
