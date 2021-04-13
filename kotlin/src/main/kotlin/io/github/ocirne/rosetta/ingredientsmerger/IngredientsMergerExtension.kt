package io.github.ocirne.rosetta.ingredientsmerger

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient

fun Grouping<Ingredient, String>.sumEach(): Map<String, Int> {
    return this.fold(0) { total, ingredient -> total + ingredient.amount }
}

class IngredientsMergerExtension : IngredientsMerger {

    override fun merge(list1: List<Ingredient>, list2: List<Ingredient>): List<Ingredient> {
        return (list1 + list2)
            .groupingBy { i -> i.name }
            .sumEach()
            .map { (name, amount) -> Ingredient(name, amount) }
    }
}
