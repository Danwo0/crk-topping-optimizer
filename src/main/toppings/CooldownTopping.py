from toppings.Topping import Topping


class CooldownTopping(Topping):

    def get_value(self):
        return 1.8 + self.level * 0.1
