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
}
