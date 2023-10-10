package com.toppingoptimizer.optimizing;

import com.toppingoptimizer.utils.StatType;

public class Configuration {
    private final StatType statType;
    private final double min;
    private final double max;

    private Configuration(ConfigurationBuilder builder) {
        this.statType = builder.statType;
        this.min = builder.min;
        this.max = builder.max;
    }

    public StatType getStatType() {
        return this.statType;
    }

    public boolean isValid(double value) {
        return (this.min < value && value < this.max);
    }

    public static class ConfigurationBuilder {
        private StatType statType;
        private double min = Double.MIN_VALUE;
        private double max = Double.MAX_VALUE;

        public static ConfigurationBuilder toBuilder() {
            return new ConfigurationBuilder();
        }

        private ConfigurationBuilder() {}

        public ConfigurationBuilder statType(StatType statType) {
            this.statType = statType;
            return this;
        }

        public ConfigurationBuilder min(double min) {
            this.min = min;
            return this;
        }

        public ConfigurationBuilder max(double max) {
            this.max = max;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
