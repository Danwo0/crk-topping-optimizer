package com.toppingoptimizer.optimizing;

import com.toppingoptimizer.toppings.SubStat;
import com.toppingoptimizer.toppings.Topping;
import com.toppingoptimizer.utils.EventType;
import com.toppingoptimizer.utils.StatType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CombinationTest {

    private Topping t1;
    private Topping t2;
    private Topping t3;
    private Topping t4;
    private Topping t5;
    private Combination c;

    @BeforeAll
    public void setUp() {
        List<SubStat> s1 = List.of(new SubStat(StatType.ATTACK, 3),
                new SubStat(StatType.ATTACK_SPEED, 4),
                new SubStat(StatType.COOLDOWN, 5));

        t1 = new Topping.ToppingBuilder()
                .mainType(StatType.ATTACK)
                .subStats(s1)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s2 = List.of(new SubStat(StatType.DEBUFF_DECREASE, 3),
                new SubStat(StatType.ATTACK, 4),
                new SubStat(StatType.HEALTH, 5));

        t2 = new Topping.ToppingBuilder()
                .mainType(StatType.ATTACK)
                .subStats(s2)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s3 = List.of(new SubStat(StatType.COOLDOWN, 3),
                new SubStat(StatType.CRIT_DAMAGE_REDUCTION, 4),
                new SubStat(StatType.ATTACK, 5));

        t3 = new Topping.ToppingBuilder()
                .mainType(StatType.ATTACK)
                .subStats(s3)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s4 = List.of(new SubStat(StatType.BUFF_INCREASE, 3),
                new SubStat(StatType.ATTACK, 4),
                new SubStat(StatType.CRIT_RATE, 5));

        t4 = new Topping.ToppingBuilder()
                .mainType(StatType.ATTACK)
                .subStats(s4)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s5 = List.of(new SubStat(StatType.ATTACK_SPEED, 3),
                new SubStat(StatType.DEBUFF_DECREASE, 4),
                new SubStat(StatType.DAMAGE_REDUCTION, 5));

        t5 = new Topping.ToppingBuilder()
                .mainType(StatType.ATTACK)
                .subStats(s5)
                .event(EventType.NONE)
                .level(12)
                .build();

        c = new Combination(List.of(t1, t2, t3, t4, t5));
    }

    @Test
    public void testGetSummary() {
        Map<StatType, Double> expected = new HashMap<>();
        expected.put(StatType.ATTACK, 66.0);
        expected.put(StatType.COOLDOWN, 8.0);
        expected.put(StatType.BUFF_INCREASE, 3.0);
        expected.put(StatType.HEALTH, 5.0);
        expected.put(StatType.DEBUFF_DECREASE, 7.0);
        expected.put(StatType.CRIT_DAMAGE_REDUCTION, 4.0);
        expected.put(StatType.DEFENSE, 0.0);
        expected.put(StatType.DAMAGE_REDUCTION, 5.0);
        expected.put(StatType.CRIT_RATE, 5.0);
        expected.put(StatType.ATTACK_SPEED, 7.0);

        assertEquals(expected, c.getSummary());
    }

    @Test
    public void testGetToppingIds() {
        List<UUID> expected = List.of(t1.getId(), t2.getId(), t3.getId(), t4.getId(), t5.getId());
        assertEquals(expected, c.getToppingIds());
    }

    @Test
    public void testMarkUsed() {
        setUp();
        assertFalse(t1.getIsUsed());

        c.markUsed(true);
        assertTrue(t1.getIsUsed());
    }
}