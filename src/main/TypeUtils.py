from enum import Enum


class StatType(Enum):
    ATTACK = "attack"
    COOLDOWN = "cooldownReduction"
    CRIT_RATE = "critRate"
    DAMAGE_REDUCTION = "damageReduction"
    ATTACK_SPEED = "attackSpeed"
    DEFENSE = "defense"
    HEALTH = "health"
    DEBUFF_DECREASE = "debuffDecrease"
    BUFF_INCREASE = "buffIncrease"
    CRIT_DAMAGE_REDUCTION = "critDamageReduction"

    @staticmethod
    def from_str(label):
        if label == StatType.ATTACK.value:
            return StatType.ATTACK
        elif label == StatType.COOLDOWN.value:
            return StatType.COOLDOWN
        elif label == StatType.CRIT_RATE.value:
            return StatType.CRIT_RATE
        elif label == StatType.DAMAGE_REDUCTION.value:
            return StatType.DAMAGE_REDUCTION
        elif label == StatType.ATTACK_SPEED.value:
            return StatType.ATTACK_SPEED
        elif label == StatType.DEFENSE.value:
            return StatType.DEFENSE
        elif label == StatType.HEALTH.value:
            return StatType.HEALTH
        elif label == StatType.DEBUFF_DECREASE.value:
            return StatType.DEBUFF_DECREASE
        elif label == StatType.BUFF_INCREASE.value:
            return StatType.BUFF_INCREASE
        elif label == StatType.CRIT_DAMAGE_REDUCTION.value:
            return StatType.CRIT_DAMAGE_REDUCTION
        else:
            raise NotImplementedError


class EventType(Enum):
    DRAGON = "dragon"
    MOON = "moonlight"
    ROCK = "tropicalRock"
    OCEAN = "oceanWave"
    NONE = "none"

    @staticmethod
    def from_str(label):
        if label == EventType.DRAGON.value:
            return EventType.DRAGON
        elif label == EventType.MOON.value:
            return EventType.MOON
        elif label == EventType.ROCK.value:
            return EventType.ROCK
        elif label == EventType.OCEAN.value:
            return EventType.OCEAN
        elif label == EventType.NONE.value:
            return EventType.NONE
        else:
            raise NotImplementedError
