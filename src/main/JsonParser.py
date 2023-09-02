import json

class JsonParser:

    def readJson(self, dir):
        file = open(dir, "r")
        return json.load(file)
