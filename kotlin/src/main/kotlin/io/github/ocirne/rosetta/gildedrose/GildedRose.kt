package io.github.ocirne.rosetta.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { updateQuality(it) }
    }

    fun updateQuality(item: Item) {
        when (item.name) {
            "Sulfuras, Hand of Ragnaros" -> LegendaryItem(item)
            "Aged Brie" -> CheeseItem(item)
            "Backstage passes to a TAFKAL80ETC concert" -> BackstagePassItem(item)
            else -> OrdinaryItem(item)
        }.age()
    }

    abstract class AgingItem(val item: Item) {
        abstract fun age()
    }

    class OrdinaryItem(item: Item): AgingItem(item) {
        override fun age() {
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

    class CheeseItem(item: Item): AgingItem(item) {
        override fun age() {
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
    }

    class BackstagePassItem(item: Item): AgingItem(item) {
        override fun age() {
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
    }

    class LegendaryItem(item: Item): AgingItem(item) {
        override fun age() {
            // do nothing
        }
    }
}
