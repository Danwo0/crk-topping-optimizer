package com.toppingoptimizer.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatType {
    @JsonProperty("attack")
    ATTACK("attack"),
    @JsonProperty("cooldownReduction")
    COOLDOWN("cooldownReduction"),
    @JsonProperty("critRate")
    CRIT_RATE("critRate"),
    @JsonProperty("damageReduction")
    DAMAGE_REDUCTION("damageReduction"),
    @JsonProperty("attackSpeed")
    ATTACK_SPEED("attackSpeed"),
    @JsonProperty("defense")
    DEFENSE("defense"),
    @JsonProperty("health")
    HEALTH("health"),
    @JsonProperty("debuffDecrease")
    DEBUFF_DECREASE("debuffDecrease"),
    @JsonProperty("buffIncrease")
    BUFF_INCREASE("buffIncrease"),
    @JsonProperty("critDamageReduction")
    CRIT_DAMAGE_REDUCTION("critDamageReduction");

    private final String value;

    StatType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
