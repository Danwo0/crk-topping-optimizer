package com.toppingoptimizer.toppings;

import com.toppingoptimizer.utils.EventType;
import com.toppingoptimizer.utils.StatType;

import java.util.List;
import java.util.UUID;

public interface ITopping {
    public UUID getId();

    public StatType getMainType();

    public List<SubStat> getSubStats();

    public void addSubStat(SubStat stat);

    public int getLevel();

    public EventType getEvent();

    public boolean getIsUsed();

    public void setIsUsed(boolean isUsed);

    public double getValue();
}
