package com.sandazzz.laboratory;

import org.junit.jupiter.api.Test;
import java.util.List;

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

}
