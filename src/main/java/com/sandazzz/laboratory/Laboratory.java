package com.sandazzz.laboratory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Laboratory {
    private final Map<String, Double>stock = new HashMap<>();
    private final Map<String, List<Map.Entry<Double, String>>> reactions = new HashMap<>();

    public Laboratory(List<String> substances) {
        this(substances, null);
    }
    public Laboratory(List<String> substances, Map<String, List<Map.Entry<Double, String>>> reactions) {

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

        if (reactions != null) {

            for (Map.Entry<String, List<Map.Entry<Double, String>>> reaction : reactions.entrySet()) {
                String product = reaction.getKey();

                if (product == null || product.isBlank()) {
                    throw new IllegalArgumentException("Product name cannot be empty.");
                }

                List<Map.Entry<Double, String>> ingredients = reaction.getValue();
                if (ingredients == null || ingredients.isEmpty()) {
                    throw new IllegalArgumentException("Reaction ingredients cannot be empty.");
                }

                for (Map.Entry<Double, String> ing : ingredients) {
                    if (ing == null || ing.getKey() <= 0 ||
                            ing.getValue() == null || ing.getValue().isBlank()) {
                        throw new IllegalArgumentException("Invalid reaction ingredient.");
                    }
                }
            }

            this.reactions.putAll(reactions);

            // rendre les produits stockables
            for (String product : reactions.keySet()) {
                stock.putIfAbsent(product, 0.0);
            }
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

    public double make(String product, double quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (!reactions.containsKey(product)) {
            throw new IllegalArgumentException("Unknown product: " + product);
        }

        List<Map.Entry<Double, String>> ingredients = reactions.get(product);

        double max = quantity;
        for (var ing : ingredients) {
            double neededPerUnit = ing.getKey();
            String name = ing.getValue();

            double available;
            if (reactions.containsKey(name)) {
                available = make(name, Double.POSITIVE_INFINITY);
            } else {
                available = stock.get(name);
            }

            max = Math.min(max, available / neededPerUnit);
        }

        for (var ing : ingredients) {
            stock.put(
                    ing.getValue(),
                    stock.get(ing.getValue()) - max * ing.getKey()
            );
        }

        stock.put(product, stock.get(product) + max);

        return max;
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
