
class GildedRose(object):

    def __init__(self, items):
        self.items = items

    def update_quality(self):
        for item in self.items:
            handle_item(item)


def handle_item(item):
    if item.name == "Aged Brie":
        handle_cheese(item)
    elif item.name == "Backstage passes to a TAFKAL80ETC concert":
        handle_backstage_pass(item)
    elif item.name == "Sulfuras, Hand of Ragnaros":
        handle_legendary(item)
    else:
        handle_default(item)


def handle_default(item):
    """
    - All items have a SellIn value which denotes the number of days we have to sell the item
    - All items have a Quality value which denotes how valuable the item is
    - At the end of each day our system lowers both values for every item
    - Once the sell by date has passed, Quality degrades twice as fast
    - The Quality of an item is never negative
    - The Quality of an item is never more than 50

    >>> aging(Item('foo', 3, 4), handle_default)
    foo, 2, 3
    >>> aging(Item('foo', 0, 10), handle_default)
    foo, -1, 8
    >>> aging(Item('foo', 3, 0), handle_default)
    foo, 2, 0
    >>> aging(Item('foo', 3, 60), handle_default)
    foo, 2, 50
    """
    item.sell_in -= 1
    delta = -2 if item.sell_in < 0 else -1
    item.quality = limit_to(item.quality + delta, 0, 50)


def handle_cheese(item):
    """
    - "Aged Brie" actually increases in Quality the older it gets.
    - The Quality of an item is never more than 50

    >>> aging(Item('foo', 3, 4), handle_cheese)
    foo, 2, 5
    >>> aging(Item('foo', 0, 4), handle_cheese)
    foo, -1, 6
    >>> aging(Item('foo', 3, 50), handle_cheese)
    foo, 2, 50
    >>> aging(Item('foo', 0, 49), handle_cheese)
    foo, -1, 50
    """
    item.sell_in -= 1
    delta = 2 if item.sell_in < 0 else 1
    item.quality = limit_to(item.quality + delta, 0, 50)


def handle_backstage_pass(item):
    """
    "Backstage passes", increases in Quality as its SellIn value approaches; Quality increases by 2 when there are
    10 days or less and by 3 when there are 5 days or less but Quality drops to 0 after the concert.

    >>> aging(Item('foo', 15, 20), handle_backstage_pass)
    foo, 14, 21
    >>> aging(Item('foo', 11, 20), handle_backstage_pass)
    foo, 10, 21
    >>> aging(Item('foo', 10, 20), handle_backstage_pass)
    foo, 9, 22
    >>> aging(Item('foo', 6, 20), handle_backstage_pass)
    foo, 5, 22
    >>> aging(Item('foo', 5, 20), handle_backstage_pass)
    foo, 4, 23
    >>> aging(Item('foo', 0, 20), handle_backstage_pass)
    foo, -1, 0
    >>> aging(Item('foo', -1, 20), handle_backstage_pass)
    foo, -2, 0
    """
    item.sell_in -= 1
    if item.sell_in < 0:
        delta = -50
    elif item.sell_in < 5:
        delta = 3
    elif item.sell_in < 10:
        delta = 2
    else:
        delta = 1
    item.quality = limit_to(item.quality + delta, 0, 50)


def handle_legendary(_):
    """
    A legendary item never has to be sold or decreases in Quality.

    >>> aging(Item('foo', 0, 80), handle_legendary)
    foo, 0, 80
    >>> aging(Item('foo', -1, 80), handle_legendary)
    foo, -1, 80
    >>> aging(Item('foo', 42, 80), handle_legendary)
    foo, 42, 80
    """
    ...


def limit_to(value: int, lower: int, upper: int):
    """
    Limits the value to the lower and upper limit (inclusive).

    >>> limit_to(42, 0, 50)
    42
    >>> limit_to(-42, 0, 50)
    0
    >>> limit_to(142, 0, 50)
    50
    >>> limit_to(0, 0, 50)
    0
    >>> limit_to(50, 0, 50)
    50
    """
    return min(max(value, lower), upper)


def aging(item, aging_fun):
    """
    Used for tests, as item's state is mutated.
    """
    aging_fun(item)
    return item


class Item:
    def __init__(self, name, sell_in, quality):
        self.name = name
        self.sell_in = sell_in
        self.quality = quality

    def __repr__(self):
        return "%s, %s, %s" % (self.name, self.sell_in, self.quality)
