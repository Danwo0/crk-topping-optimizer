package com.toppingoptimizer.optimizing;

import com.toppingoptimizer.toppings.SubStat;
import com.toppingoptimizer.toppings.Topping;
import com.toppingoptimizer.utils.CombinationWorker;
import com.toppingoptimizer.utils.EventType;
import com.toppingoptimizer.utils.StatType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ComparerTest {

    private Comparer comparer;
    List<Topping> toppings;

    @BeforeEach
    public void setUp() {
         Configuration c1 = Configuration.ConfigurationBuilder.toBuilder().statType(StatType.ATTACK).build();

        List<SubStat> s1 = List.of(new SubStat(StatType.ATTACK, 5),
                new SubStat(StatType.COOLDOWN, 2),
                new SubStat(StatType.CRIT_RATE, 2));

        Topping t1 = Topping.ToppingBuilder.toBuilder()
                .mainType(StatType.ATTACK)
                .subStats(s1)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s2 = List.of(new SubStat(StatType.ATTACK, 2),
                new SubStat(StatType.COOLDOWN, 5),
                new SubStat(StatType.CRIT_RATE, 2));

        Topping t2 = Topping.ToppingBuilder.toBuilder()
                .mainType(StatType.ATTACK)
                .subStats(s2)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s3 = List.of(new SubStat(StatType.ATTACK, 2),
                new SubStat(StatType.COOLDOWN, 2),
                new SubStat(StatType.CRIT_RATE, 5));

        Topping t3 = Topping.ToppingBuilder.toBuilder()
                .mainType(StatType.ATTACK)
                .subStats(s3)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s4 = List.of(new SubStat(StatType.ATTACK, 5),
                new SubStat(StatType.COOLDOWN, 5),
                new SubStat(StatType.CRIT_RATE, 2));

        Topping t4 = Topping.ToppingBuilder.toBuilder()
                .mainType(StatType.COOLDOWN)
                .subStats(s4)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s5 = List.of(new SubStat(StatType.ATTACK, 5),
                new SubStat(StatType.COOLDOWN, 2),
                new SubStat(StatType.CRIT_RATE, 5));

        Topping t5 = Topping.ToppingBuilder.toBuilder()
                .mainType(StatType.COOLDOWN)
                .subStats(s5)
                .event(EventType.NONE)
                .level(12)
                .build();

        List<SubStat> s6 = List.of(new SubStat(StatType.ATTACK, 2),
                new SubStat(StatType.COOLDOWN, 5),
                new SubStat(StatType.CRIT_RATE, 5));

        Topping t6 = Topping.ToppingBuilder.toBuilder()
                .mainType(StatType.COOLDOWN)
                .subStats(s6)
                .event(EventType.NONE)
                .level(12)
                .build();

        toppings = List.of(t1, t2, t3, t4, t5, t6);

        CombinationWorker generator = new CombinationWorker(toppings.size(), 5);

        comparer = new Comparer(List.of(c1), toppings, generator);
    }

    @Test
    public void testGetBestNotDone() {
        assertNull(comparer.getBest());
    }

    @Test
    public void testGetBestDone() {
        assertNull(comparer.getBest());
        comparer.start();

        await().atMost(250, TimeUnit.MILLISECONDS)
                .pollInterval(50, TimeUnit.MICROSECONDS)
                .until(() -> comparer.getState().equals(Thread.State.TERMINATED));

        List<Topping> expectedToppings = List.of(toppings.get(0), toppings.get(1), toppings.get(2), toppings.get(3), toppings.get(4));
        Combination expected = new Combination(expectedToppings);
        assertEquals(expected, comparer.getBest());
    }

    @Test
    public void testGetCount() {
        assertEquals(0, comparer.getCount());
        comparer.start();

        await().atMost(250, TimeUnit.MILLISECONDS)
                .pollInterval(50, TimeUnit.MICROSECONDS)
                .until(() -> comparer.getState().equals(Thread.State.TERMINATED));

        assertEquals(6, comparer.getCount());
    }

}