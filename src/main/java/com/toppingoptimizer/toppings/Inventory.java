package com.toppingoptimizer.toppings;

import com.toppingoptimizer.utils.StatType;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements IStorage {
    private List<Topping> toppings;

    public Inventory(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public Inventory() {
        this.toppings = new ArrayList<>();
    }

    public List<Topping> getToppings(boolean getUsed) {
        return this.toppings.stream().filter(topping -> !topping.getIsUsed()).toList();
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public List<Topping> getToppingType(List<StatType> statTypes, boolean getUsed) {
        return this.toppings
                .stream()
                .filter(topping -> statTypes.stream().anyMatch(t -> (topping.getMainType() == t && (getUsed || !topping.getIsUsed()))))
                .toList();
    }
}
