from toppings.Topping import Topping


class CritRateTopping(Topping):

    def get_value(self):
        return 3 + self.level * 0.5
