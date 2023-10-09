import itertools

from TypeUtils import StatType


class Optimizer:

    def __init__(self, toppings):
        self.toppings = toppings
        self.combinations = list(itertools.combinations(toppings, 5))

    def optimize(self, primary_stat, secondary_stat):
        best_combination = None
        max_value_primary = float('-inf')
        max_value_secondary = float('-inf')

        for combination in self.combinations:
            current_value_primary = 0
            current_value_secondary = 0

            for topping in combination:
                current_value_primary += self.__get_topping_value(topping, primary_stat)
                current_value_secondary += self.__get_topping_value(topping, secondary_stat)

            synergy_value = self.__check_synergy(combination, primary_stat, secondary_stat)
            current_value_primary += synergy_value[0]
            current_value_secondary += synergy_value[1]

            if current_value_primary > max_value_primary:
                max_value_primary = current_value_primary
                max_value_secondary = current_value_secondary
                best_combination = combination
            elif current_value_primary == max_value_primary and current_value_secondary > max_value_secondary:
                max_value_primary = current_value_primary
                max_value_secondary = current_value_secondary
                best_combination = combination

        print(max_value_primary, max_value_secondary)
        return best_combination

    def __get_topping_value(self, topping, stat_type):
        value = 0

        if topping.main_type == stat_type:
            value += topping.get_value()

        for sub_stat in topping.sub_stats:
            if sub_stat.stat_type == stat_type:
                value += sub_stat.value
                return value

        return value

    def __check_synergy(self, combination, primary_stat, secondary_stat):
        count = [0, 0, 0, 0, 0]
        for topping in combination:
            if topping.main_type == StatType.ATTACK:
                count[0] += 1
            elif topping.main_type == StatType.COOLDOWN:
                count[1] += 1
            elif topping.main_type == StatType.CRIT_RATE:
                count[2] += 1
            elif topping.main_type == StatType.DAMAGE_REDUCTION:
                count[3] += 1
            elif topping.main_type == StatType.ATTACK_SPEED:
                count[4] += 1

        synergy = [0, 0]

        if count[0] == 5:
            if primary_stat == StatType.ATTACK:
                synergy[0] += 5
            elif secondary_stat == StatType.ATTACK:
                synergy[1] += 5
        elif count[0] >= 3:
            if primary_stat == StatType.ATTACK:
                synergy[0] += 3
            elif secondary_stat == StatType.ATTACK:
                synergy[1] += 3

        if count[1] == 5:
            if primary_stat == StatType.COOLDOWN:
                synergy[0] += 5
            elif secondary_stat == StatType.COOLDOWN:
                synergy[1] += 5

        if count[2] == 5:
            if primary_stat == StatType.CRIT_RATE:
                synergy[0] += 5
            elif secondary_stat == StatType.CRIT_RATE:
                synergy[1] += 5

        if count[3] == 5:
            if primary_stat == StatType.DAMAGE_REDUCTION:
                synergy[0] += 5
            elif secondary_stat == StatType.DAMAGE_REDUCTION:
                synergy[1] += 5

        if count[4] == 5:
            if primary_stat == StatType.ATTACK_SPEED:
                synergy[0] += 2
            elif secondary_stat == StatType.ATTACK_SPEED:
                synergy[1] += 2
        elif count[4] >= 2:
            if primary_stat == StatType.ATTACK_SPEED:
                synergy[0] += 1
            elif secondary_stat == StatType.ATTACK_SPEED:
                synergy[1] += 1

        return synergy
