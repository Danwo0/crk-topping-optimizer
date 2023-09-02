from toppings.Topping import Topping


class AttackSpeedTopping(Topping):

    def get_value(self):
        return 1.7 + self.level * 0.2
