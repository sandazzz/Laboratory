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
        Laboratory lab = new Laboratory(List.of("eau", ""));
    }

    @Test
    void testGetQuantityUnknownSubstanceThrows() {
        Laboratory lab = new Laboratory(List.of("eau", "sel"));
        assertThrows(IllegalArgumentException.class, () -> lab.getQuantity("sucre"));
    }
}
