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
            else -> AgingItem(item)
        }.updateQuality()
    }

    open class AgingItem(val item: Item) {

        fun updateQuality() {
            age()
            after()
        }

        open fun after() {
            item.quality = (item.quality + getDeltaQuality()).coerceIn(0, 50)
        }

        open fun getDeltaQuality(): Int {
            return if (item.sellIn < 0) -2 else -1
        }

        open fun age() {
            item.sellIn--
        }
    }

    class CheeseItem(item: Item): AgingItem(item) {

        override fun getDeltaQuality(): Int {
            return if (item.sellIn < 0) +2 else -1
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

    class LegendaryItem(item: Item): AgingItem(item) {

        override fun after() {
            // do nothing
        }

        override fun age() {
            // do nothing
        }
    }
}
