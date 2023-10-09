package com.toppingoptimizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toppingoptimizer.toppings.Inventory;
import com.toppingoptimizer.toppings.Topping;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class DataManager {

    private Inventory inventory;

    public DataManager() {
        inventory = new Inventory();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addTopping(Topping t) {
        this.inventory.addTopping(t);
    }

    public Inventory load(String source) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.inventory = mapper.readValue(new FileReader(source), Inventory.class);
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading and parsing inventory data");
        }

        return this.inventory;
    }

    public void save(String destination) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(destination), this.inventory);
        } catch(IOException e) {
            throw new RuntimeException("Error saving inventory data to file");
        }
    }
}
