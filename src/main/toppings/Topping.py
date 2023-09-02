class Topping:

    def __init__(self, main_type, level, sub_stats, event=None):
        self.main_type = main_type
        self.level = level
        self.sub_stats = sub_stats
        self.event = event

    def __repr__(self):
        return f'{self.main_type} +{self.level}, sub stats = {self.sub_stats}'

    def get_value(self):
        pass
