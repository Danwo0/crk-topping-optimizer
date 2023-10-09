class SubStat:

    def __init__(self, stat_type, value):
        self.stat_type = stat_type
        self.value = value

    def __repr__(self):
        return f'{self.stat_type} {self.value}%'
