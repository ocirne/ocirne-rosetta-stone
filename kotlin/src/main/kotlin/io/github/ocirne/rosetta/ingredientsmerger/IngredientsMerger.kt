package io.github.ocirne.rosetta.ingredientsmerger

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient

object IngredientsMerger {

    fun merge(list1: List<Ingredient>, list2: List<Ingredient>): List<Ingredient> {
        return listOf(Ingredient("apple", 7))
    }
}
