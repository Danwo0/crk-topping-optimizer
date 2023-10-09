package com.toppingoptimizer.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EventType {
    @JsonProperty("dragon")
    DRAGON("dragon"),
    @JsonProperty("moonlight")
    MOON("moonlight"),
    @JsonProperty("tropicalRock")
    ROCK("tropicalRock"),
    @JsonProperty("oceanWave")
    OCEAN("oceanWave"),
    @JsonProperty("none")
    NONE("none");

    private final String eventName;

    EventType(String eventName) {
        this.eventName = eventName;
    }

    public String getValue() {
        return this.eventName;
    }
}
