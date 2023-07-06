package io.github.ocirne.rosetta.gildedrose

/*
 * However, do not alter the Item class or Items property as those belong to the goblin in the corner who will
 * insta-rage and one-shot you as he doesn't believe in shared code ownership.
 */
open class Item(var name: String, var sellIn: Int, var quality: Int) {
    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }
}