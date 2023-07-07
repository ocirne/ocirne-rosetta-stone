package io.github.ocirne.rosetta.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { updateQuality(it) }
    }

    fun updateQuality(item: Item) {
        when (item.name) {
            "Sulfuras, Hand of Ragnaros" -> LegendaryItem()
            "Aged Brie" -> CheeseItem(item)
            "Backstage passes to a TAFKAL80ETC concert" -> BackstagePassItem(item)
            "Conjured Mana Cake" -> ConjuredItem(item)
            else -> AgingItem(item)
        }.age()
    }

    interface Aging {
        fun age()
    }

    open class AgingItem(val item: Item): Aging {

        override fun age() {
            item.sellIn--
            item.quality = (item.quality + getDeltaQuality()).coerceIn(0, 50)
        }

        open fun getDeltaQuality(): Int {
            return if (item.sellIn < 0) -2 else -1
        }
    }

    class CheeseItem(item: Item): AgingItem(item) {

        override fun getDeltaQuality(): Int {
            return if (item.sellIn < 0) +2 else +1
        }
    }

    class ConjuredItem(item: Item): AgingItem(item) {

        override fun getDeltaQuality(): Int {
            return if (item.sellIn < 0) -4 else -2
        }
    }

    class BackstagePassItem(item: Item): AgingItem(item) {

        override fun getDeltaQuality(): Int {
            return when {
                item.sellIn < 0 -> -50
                item.sellIn < 5 -> +3
                item.sellIn < 10 -> +2
                else -> +1
            }
        }
    }

    class LegendaryItem: Aging {

        override fun age() {
            // do nothing
        }
    }
}
