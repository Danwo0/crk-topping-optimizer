from FileManager import FileManager
from Optimizer import Optimizer
from TypeUtils import StatType

if __name__ == '__main__':
    manager = FileManager()

    toppings = manager.read_data("src/res/data.json")

    optimizer = Optimizer(toppings)
    print(optimizer.optimize(StatType.ATTACK_SPEED, StatType.ATTACK))
