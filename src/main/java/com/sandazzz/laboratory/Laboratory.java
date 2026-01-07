package com.sandazzz.laboratory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Laboratory {
    private final Map<String, Double>stock = new HashMap<>();

    public Laboratory(List<String> substances) {
        if (substances == null || substances.isEmpty()) {
            throw new IllegalArgumentException("Substance list cannot be null or empty.");
        }

        for (String s : substances) {
            if (s.isEmpty()){
                throw new IllegalArgumentException("Empty substance list");
            }

            if (stock.containsKey(s)) {
                throw new IllegalArgumentException("Duplicate substance: " + s);
            }

            stock.put(s, 0.0);
        }
    }

    public double getQuantity(String substance) {
        if (!stock.containsKey(substance)) {
            throw new IllegalArgumentException("Unknown substance: " + substance);
        }
        System.out.println(stock.get(substance));
        return stock.get(substance);
    }

    public void add( String substance, double quantity) {
        validateSubstanceName(substance);
        validateKnownSubstance(substance);
        validateQuantity(quantity);

        stock.put(substance, stock.get(substance) + quantity);
    }

    private void validateSubstanceName(String substance) {
        if (substance == null || substance.isBlank()) {
            throw new IllegalArgumentException("Substance name cannot be empty.");
        }
    }

    private void validateKnownSubstance(String substance) {
        if (!stock.containsKey(substance)) {
            throw new IllegalArgumentException("Unknown substance: " + substance);
        }
    }

    private void validateQuantity(double quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
    }
}
