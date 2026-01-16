package com.sandazzz.laboratory;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LaboratoryTest {

    @Test
    void testInitializationWithValidSubstances() {
        assertDoesNotThrow(() -> new Laboratory(List.of("eau", "sel", "sucre")));
    }

    @Test
    void testInitialQuantityIsZero() {
        Laboratory lab = new Laboratory(List.of("eau", "sel"));
        assertEquals(0.0, lab.getQuantity("eau"));
    }

    @Test
    void testEmptySubstance() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Laboratory(List.of("eau", ""))
        );

        assertEquals("Empty substance list", ex.getMessage());
    }

    @Test
    void testGetQuantityUnknownSubstanceThrows() {
        Laboratory lab = new Laboratory(List.of("eau", "sel"));
        assertThrows(IllegalArgumentException.class, () -> lab.getQuantity("sucre"));
    }

    @Test
    void testInitializationWithDuplicateSubstancesThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Laboratory(List.of("eau", "eau"))
        );
    }

    @Test
    void testInitializationWithEmptyListThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Laboratory(List.of())
        );
    }

    @Test
    void testInitializationWithNullListThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Laboratory(null)
        );
    }

    @Test
    void testAddIncreasesQuantity() {
        Laboratory lab = new Laboratory(List.of("eau", "sel"));

        lab.add("eau", 1.5);

        assertEquals(1.5, lab.getQuantity("eau"));
    }

    @Test
    void testAddIsCumulative() {
        Laboratory lab = new Laboratory(List.of("eau"));

        lab.add("eau", 1.0);
        lab.add("eau", 3.5);

        assertEquals(4.5, lab.getQuantity("eau"));
    }

    @Test
    void testAddUnknownSubstanceThrows() {
        Laboratory lab = new Laboratory(List.of("eau"));

        assertThrows(IllegalArgumentException.class, () ->
                lab.add("sel", 1.0)
        );
    }

    @Test
    void testAddNegativeQuantityThrows() {
        Laboratory lab = new Laboratory(List.of("eau"));

        assertThrows(IllegalArgumentException.class, () ->
                lab.add("eau", -1.0)
        );
    }

    @Test
    void testAddZeroQuantityThrows() {
        Laboratory lab = new Laboratory(List.of("eau"));

        assertThrows(IllegalArgumentException.class, () ->
                lab.add("eau", 0.0)
        );
    }
    @Test
    void testAddWithNullSubstanceThrows() {
        Laboratory lab = new Laboratory(List.of("eau"));

        assertThrows(IllegalArgumentException.class, () ->
                lab.add(null, 1.0)
        );
    }

    @Test
    void testAddWithEmptySubstanceThrows() {
        Laboratory lab = new Laboratory(List.of("eau"));

        assertThrows(IllegalArgumentException.class, () ->
                lab.add("", 1.0)
        );
    }

    @Test
    void testInitializationWithValidReactions() {
        List<String> substances = List.of("hydrogen", "oxygen");

        Map<String, List<Map.Entry<Double, String>>> reactions = Map.of(
                "water", List.of(
                        Map.entry(2.0, "hydrogen"),
                        Map.entry(1.0, "oxygen")
                )
        );

        assertDoesNotThrow(() -> new Laboratory(substances, reactions));
    }

    @Test
    void testProductIsInitializedWithZeroQuantity() {
        List<String> substances = List.of("hydrogen", "oxygen");

        Map<String, List<Map.Entry<Double, String>>> reactions = Map.of(
                "water", List.of(
                        Map.entry(2.0, "hydrogen"),
                        Map.entry(1.0, "oxygen")
                )
        );

        Laboratory lab = new Laboratory(substances, reactions);

        assertEquals(0.0, lab.getQuantity("water"));
    }

    @Test
    void testMakeUnknownProductThrows() {
        Laboratory lab = new Laboratory(List.of("hydrogen", "oxygen"));

        assertThrows(IllegalArgumentException.class,
                () -> lab.make("water", 1.0));
    }

    @Test
    void testMakeWithNonPositiveQuantityThrows() {
        List<String> substances = List.of("hydrogen", "oxygen");

        Map<String, List<Map.Entry<Double, String>>> reactions = Map.of(
                "water", List.of(
                        Map.entry(2.0, "hydrogen"),
                        Map.entry(1.0, "oxygen")
                )
        );

        Laboratory lab = new Laboratory(substances, reactions);

        assertThrows(IllegalArgumentException.class,
                () -> lab.make("water", 0.0));
    }

    @Test
    void testMakeConsumesIngredientsAndProducesProduct() {
        List<String> substances = List.of("hydrogen", "oxygen");

        Map<String, List<Map.Entry<Double, String>>> reactions = Map.of(
                "water", List.of(
                        Map.entry(2.0, "hydrogen"),
                        Map.entry(1.0, "oxygen")
                )
        );

        Laboratory lab = new Laboratory(substances, reactions);
        lab.add("hydrogen", 10);
        lab.add("oxygen", 5);

        double produced = lab.make("water", 2);

        assertEquals(2.0, produced);
        assertEquals(6.0, lab.getQuantity("hydrogen"));
        assertEquals(3.0, lab.getQuantity("oxygen"));
        assertEquals(2.0, lab.getQuantity("water"));
    }

    @Test
    void testMakeWithProductAsIngredient() {
        List<String> substances = List.of("hydrogen", "oxygen");

        Map<String, List<Map.Entry<Double, String>>> reactions = Map.of(
                "B", List.of(
                        Map.entry(2.0, "hydrogen")
                ),
                "A", List.of(
                        Map.entry(1.0, "B"),
                        Map.entry(1.0, "oxygen")
                )
        );

        Laboratory lab = new Laboratory(substances, reactions);
        lab.add("hydrogen", 10);
        lab.add("oxygen", 5);

        double produced = lab.make("A", 2);

        assertEquals(2.0, produced);
        assertEquals(6.0, lab.getQuantity("hydrogen"));
        assertEquals(3.0, lab.getQuantity("oxygen"));
        assertEquals(2.0, lab.getQuantity("A"));
    }
}
