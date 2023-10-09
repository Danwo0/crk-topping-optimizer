package com.toppingoptimizer.toppings;

import com.toppingoptimizer.utils.EventType;
import com.toppingoptimizer.utils.StatType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Topping implements ITopping {
    private final UUID id;
    private final StatType mainType;
    private final List<SubStat> subStats;
    private final int level;
    private final EventType event;
    private boolean isUsed;

    public UUID getId() {
        return this.id;
    }

    public StatType getMainType() {
        return this.mainType;
    }

    public List<SubStat> getSubStats() {
        return this.subStats;
    }

    public void addSubStat(SubStat stat) {
        this.subStats.add(stat);
    }

    public int getLevel() {
        return this.level;
    }

    public EventType getEvent() {
        return this.event;
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public double getValue() {
        switch (mainType) {
            case ATTACK, CRIT_RATE -> {
                return 3 + this.level * 0.5;
            }
            case COOLDOWN -> {
                return 1.8 + this.level * 0.1;
            }
            case DAMAGE_REDUCTION, ATTACK_SPEED -> {
                return 1.7 + this.level * 0.2;
            }
            default -> {
                return 0;
            }
        }
    }

    public Topping() {
        this.mainType = null;
        this.subStats = List.of();
        this.level = 1;
        this.event = EventType.NONE;
        this.isUsed = false;
        this.id = UUID.randomUUID();
    }

    protected Topping(ToppingBuilder builder) {
        this.mainType = builder.mainType;
        this.subStats = builder.subStats;
        this.level = builder.level;
        this.event = builder.event;
        this.isUsed = false;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return String.format("%s: %s, %s", this.mainType, this.level, this.subStats.toString());
    }

    public static class ToppingBuilder {
        private StatType mainType;
        private List<SubStat> subStats = new ArrayList<>();

        private int level = 0;
        private EventType event = null;

        public ToppingBuilder toBuilder() {
            return this;
        }

        public ToppingBuilder mainType(StatType mainType) {
            this.mainType = mainType;
            return this;
        }

        public ToppingBuilder subStat(SubStat subStat) {
            this.subStats.add(subStat);
            return this;
        }

        public ToppingBuilder subStats(List<SubStat> subStats) {
            this.subStats = subStats;
            return this;
        }

        public ToppingBuilder level(int level) {
            this.level = level;
            return this;
        }

        public ToppingBuilder event(EventType event) {
            this.event = event;
            return this;
        }

        public Topping build() {
            return new Topping(this);
        }
    }
}
