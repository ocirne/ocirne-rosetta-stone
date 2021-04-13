package io.github.ocirne.rosetta.ingredientsmerger

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient

interface IngredientsMerger {

    fun merge(list1: List<Ingredient>, list2: List<Ingredient>): List<Ingredient>;
}
