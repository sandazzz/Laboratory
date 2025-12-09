package com.sandazzz.laboratory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Laboratory {
    private final Map<String, Double>stock = new HashMap<>();

    public Laboratory(List<String> substances) {
        for (String s : substances) {
            stock.put(s, 0.0);
        }
    }
     public double getQuantity(String substance) {
        return stock.get(substance);
     }
}
