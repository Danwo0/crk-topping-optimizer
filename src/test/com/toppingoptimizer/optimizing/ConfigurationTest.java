package com.toppingoptimizer.optimizing;

import com.toppingoptimizer.utils.StatType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    public void testIsValid() {
        Configuration noLimit = Configuration.toBuilder().statType(StatType.ATTACK).build();

        assertTrue(noLimit.isValid(1.1));
        assertTrue(noLimit.isValid(2.1));

        Configuration minLimit = Configuration.toBuilder().statType(StatType.ATTACK).min(10.0).build();

        assertTrue(minLimit.isValid(10.01));
        assertFalse(minLimit.isValid(5.0));

        Configuration maxLimit = Configuration.toBuilder().statType(StatType.ATTACK).max(50.0).build();

        assertTrue(maxLimit.isValid(49.9));
        assertTrue(maxLimit.isValid(51.1));

        Configuration limits = Configuration.toBuilder().statType(StatType.ATTACK).min(10.0).max(50.0).build();
        assertTrue(limits.isValid(10.01));
        assertTrue(limits.isValid(49.9));
        assertTrue(limits.isValid(51.1));
        assertFalse(limits.isValid(5.0));
    }
}