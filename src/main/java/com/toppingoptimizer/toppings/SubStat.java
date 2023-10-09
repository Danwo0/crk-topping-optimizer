package com.toppingoptimizer.toppings;

import com.toppingoptimizer.utils.StatType;

public class SubStat implements IStat {
    private StatType type;
    private double value;

    public SubStat(StatType type, double value) {
        this.type = type;
        this.value = value;
    }

    public SubStat() {
        this.type = null;
        this.value = 0;
    }

    public StatType getType(){
        return this.type;
    }

    public void setType(StatType type) {
        this.type = type;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
        return String.format("%s %s", this.type, this.value);
    }
}
