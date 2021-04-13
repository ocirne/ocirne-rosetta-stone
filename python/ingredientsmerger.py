from collections import defaultdict
from itertools import groupby


class Ingredient:
    name: str
    amount: int

    def __init__(self, name, amount):
        self.name = name
        self.amount = amount


def ingredients_merger_itertools(list1, list2):
    """
    >>> a = [Ingredient("apple", 3), Ingredient("banana", 1)]
    >>> b = [Ingredient("apple", 5), Ingredient("pear", 1)]
    >>> m = ingredients_merger_itertools(a, b)
    >>> apples = [i for i in m if i.name == "apple"][0]
    >>> apples.amount
    8
    """
    sorted_ingredients = sorted(list1 + list2, key=lambda i: i.name)
    groups = groupby(sorted_ingredients, key=lambda i: i.name)
    return [Ingredient(name, sum(i.amount for i in ingredients)) for name, ingredients in groups]


def ingredients_merger_collections(list1, list2):
    """
    >>> a = [Ingredient("apple", 3), Ingredient("banana", 1)]
    >>> b = [Ingredient("apple", 5), Ingredient("pear", 1)]
    >>> m = ingredients_merger_collections(a, b)
    >>> apples = [i for i in m if i.name == "apple"][0]
    >>> apples.amount
    8
    """
    dd = defaultdict(lambda: 0)
    for ingredient in list1 + list2:
        dd[ingredient.name] += ingredient.amount
    return [Ingredient(name, amount) for name, amount in dd.items()]
