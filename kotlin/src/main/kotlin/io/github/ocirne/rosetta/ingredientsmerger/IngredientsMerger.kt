package io.github.ocirne.rosetta.ingredientsmerger

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient

object IngredientsMerger {

    fun merge(list1: List<Ingredient>, list2: List<Ingredient>): List<Ingredient> {
        return (list1 + list2)
            .groupingBy { i -> i.name }
            .fold(0) { total, ingredient -> total + ingredient.amount }
            .map { (name, amount) -> Ingredient(name, amount) }
    }
}
