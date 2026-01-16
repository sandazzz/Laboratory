package com.sandazzz.laboratory;

import java.util.Map;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> substances = List.of("hydrogen", "oxygen");

        Map<String, List<Map.Entry<Double, String>>> reactions = Map.of(
                "water", List.of(
                        Map.entry(2.0, "hydrogen"),
                        Map.entry(1.0, "oxygen")
                )
        );

        Laboratory lab = new Laboratory(substances, reactions);
        lab.add("hydrogen", 5.0);
        lab.add("oxygen", 3.0);
        lab.add("water", 2.5);

        System.out.println("Hydrogen: " + lab.getQuantity("hydrogen"));
        System.out.println("Oxygen: " + lab.getQuantity("oxygen"));
        System.out.println("Water: " + lab.getQuantity("water"));
    }
}