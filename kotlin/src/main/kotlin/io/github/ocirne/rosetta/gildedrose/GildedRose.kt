package io.github.ocirne.rosetta.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") {
                handleLegendaryItem(item)
                continue
            }
            if (item.name == "Aged Brie") {
                handleAgedBrie(item)
                continue
            }
            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                handleBackstagePass(item)
                continue
            }
            handleDefault(item)
        }
    }

    fun handleLegendaryItem(item: Item) {
        // do nothing
    }

    fun handleAgedBrie(item: Item) {
        if (item.name != "Aged Brie") {
            return
        }
        if (item.quality < 50) {
            item.quality = item.quality + 1

        }
        item.sellIn = item.sellIn - 1
        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality = item.quality + 1
            }
        }
    }

    fun handleBackstagePass(item: Item) {
        if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
            return
        }
        if (item.quality < 50) {
            item.quality = item.quality + 1

            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1
                }
            }

            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1
                }
            }
        }

        item.sellIn = item.sellIn - 1

        if (item.sellIn < 0) {
            item.quality = 0
        }
    }

    fun handleDefault(item: Item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1
        }

        item.sellIn = item.sellIn - 1

        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 1
            }
        }
    }
}
