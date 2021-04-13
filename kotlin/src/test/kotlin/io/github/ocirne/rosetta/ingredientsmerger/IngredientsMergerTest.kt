package io.github.ocirne.rosetta.ingredientsmerger

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

internal object IngredientsMergerTest {

    @Test
    fun `can merge ingredients`() {
        val list1 = listOf(Ingredient("apple", 3), Ingredient("banana", 1))
        val list2 = listOf(Ingredient("apple", 5), Ingredient("pear", 1))
        val result = IngredientsMerger.merge(list1, list2)
        val apples = result.find { it.name == "apple" }
        apples shouldNotBe null
        apples!!.amount shouldBe 8
    }
}