package com.toppingoptimizer.toppings;

import com.toppingoptimizer.utils.StatType;

import java.util.List;

public interface IStorage {

    public void addTopping(Topping topping);

    public void setToppings(List<Topping> toppings);

    public List<Topping> getToppings(boolean getUsed);

    public List<Topping> getToppingType(List<StatType> statTypes, boolean getUsed);

}
