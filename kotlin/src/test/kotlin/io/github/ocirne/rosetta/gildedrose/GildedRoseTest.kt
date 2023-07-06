package io.github.ocirne.rosetta.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class GildedRoseTest {

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("getOrdinaryItems")
    fun `Aging an ordinary item`(item: Item, qualities: List<Int>) {
        val startSellIn = item.sellIn
        val app = GildedRose(listOf(item))
        qualities.forEachIndexed { day, expectedQuality ->
            val expectedSellIn = startSellIn - day
            assertEquals(expectedQuality, item.quality, "${item.name} should have quality $expectedQuality after $day days")
            assertEquals(expectedSellIn, item.sellIn, "${item.name} should have sellIn $expectedSellIn after $day days")
            app.updateQuality()
        }
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("getLegendaryItem")
    fun `Sulfuras never ages`(item: Item, qualities: List<Int>) {
        val expectedSellIn = item.sellIn
        val app = GildedRose(listOf(item))
        qualities.forEachIndexed { day, expectedQuality ->
            assertEquals(expectedQuality, item.quality, "${item.name} should have quality $expectedQuality after $day days")
            assertEquals(expectedSellIn, item.sellIn, "${item.name} should have sellIn $expectedSellIn after $day days")
            app.updateQuality()
        }
    }

    companion object {
        @JvmStatic
        fun getOrdinaryItems(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Item("+5 Dexterity Vest", 10, 20), listOf(20, 19, 18, 17, 16)),
                // "Aged Brie" actually increases in Quality the older it gets
                Arguments.of(Item("Aged Brie", 2, 0), listOf(0, 1, 2, 4, 6, 8, 10)),
                Arguments.of(Item("Elixir of the Mongoose", 5, 7), listOf(7, 6, 5, 4, 3)),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), listOf(20, 21, 22, 23, 24)),
                // Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 10, 49), listOf(49, 50, 50, 50, 50)),
                // Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 10, 0), listOf(0, 2, 4, 6, 8, 10, 13, 16, 19, 22, 25, 0, 0, 0, 0)),
                // Quality drops to 0 after the concert
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 5, 49), listOf(49, 50, 50, 50, 50, 50, 0, 0)),
                // At the end of each day our system lowers both values for every item
                Arguments.of(Item("Normal Item", 10, 20), listOf(20, 19, 18, 17, 16)),
                // Once the sell by date has passed, Quality degrades twice as fast
                Arguments.of(Item("Normal Item", 0, 20), listOf(20, 18, 16, 14, 12)),
                // The Quality of an item is never negative
                Arguments.of(Item("Normal Item", 10, 2), listOf(2, 1, 0, 0, 0, 0)),
                Arguments.of(Item("Normal Item", 0, 2), listOf(2, 0, 0, 0, 0)),
                // The Quality of an item is never more than 50
                Arguments.of(Item("Aged Brie", 0, 47), listOf(47, 49, 50, 50, 50)),
                Arguments.of(Item("Aged Brie", 2, 47), listOf(47, 48, 49, 50, 50, 50)),
                // "Conjured" items degrade in Quality twice as fast as normal items
                Arguments.of(Item("Conjured Mana Cake", 10, 6), listOf(6, 4, 2, 0, 0, 0)),
                Arguments.of(Item("Conjured Mana Cake", 10, 6), listOf(6, 4, 2, 0, 0, 0)),
            )
        }

        @JvmStatic
        fun getLegendaryItem(): Stream<Arguments> {
            return Stream.of(
                // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
                Arguments.of(Item("Sulfuras, Hand of Ragnaros", 0, 80), listOf(80, 80, 80, 80, 80)),
                Arguments.of(Item("Sulfuras, Hand of Ragnaros", -1, 80), listOf(80, 80, 80, 80, 80)),
                Arguments.of(Item("Sulfuras, Hand of Ragnaros", 42, 80), listOf(80, 80, 80, 80, 80)),
            )
        }
    }
}
