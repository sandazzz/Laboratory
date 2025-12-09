package com.sandazzz.laboratory;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LaboratoryTest {

    @Test
    void testInitializationWithValidSubstances() {
        assertDoesNotThrow(() -> new Laboratory(List.of("eau", "sel", "sucre")));
    }
}
