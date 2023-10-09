from JsonParser import JsonParser
from SubStat import SubStat
from toppings.AttackSpeedTopping import AttackSpeedTopping
from toppings.AttackTopping import AttackTopping
from toppings.CooldownTopping import CooldownTopping
from toppings.CritRateTopping import CritRateTopping
from toppings.DamageReductionTopping import DamageReductionTopping
from toppings.Topping import Topping
from TypeUtils import StatType, EventType


class FileManager:

    def __init__(self):
        self.parser = JsonParser();

    def read_data(self, dir):
        data = self.parser.readJson(dir)
        toppings = []

        for entry in data.get('toppings'):
            main_type = StatType.from_str(entry.get('mainType'))
            level = entry.get('level')
            sub_stats = self.__parse_sub_stats(entry.get('substats'))
            event = entry.get('event') if entry.get('event') is not None else "none"

            if main_type == StatType.ATTACK:
                topping = AttackTopping(main_type, level, sub_stats, EventType.from_str(event))
            elif main_type == StatType.COOLDOWN:
                topping = CooldownTopping(main_type, level, sub_stats, EventType.from_str(event))
            elif main_type == StatType.CRIT_RATE:
                topping = CritRateTopping(main_type, level, sub_stats, EventType.from_str(event))
            elif main_type == StatType.DAMAGE_REDUCTION:
                topping = DamageReductionTopping(main_type, level, sub_stats, EventType.from_str(event))
            elif main_type == StatType.ATTACK_SPEED:
                topping = AttackSpeedTopping(main_type, level, sub_stats, EventType.from_str(event))
            else:
                topping = Topping(main_type, level, sub_stats, EventType.from_str(event))

            toppings.append(topping)

        return toppings

    def __parse_sub_stats(self, data):
        sub_stats = []
        for entry in data:

            stat_type = StatType.from_str(entry.get('type'))
            value = entry.get('value')

            sub_stat = SubStat(stat_type, value)
            sub_stats.append(sub_stat)

        return sub_stats