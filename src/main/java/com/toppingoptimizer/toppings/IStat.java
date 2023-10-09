package com.toppingoptimizer.toppings;

import com.toppingoptimizer.utils.StatType;

public interface IStat {

    public StatType getType();

    public void setType(StatType type);

    public double getValue();

    public void setValue(double value);
}
