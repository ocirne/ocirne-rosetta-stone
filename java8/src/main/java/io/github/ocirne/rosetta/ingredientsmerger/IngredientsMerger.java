package io.github.ocirne.rosetta.ingredientsmerger;

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient;

import java.util.List;

interface IngredientsMerger {

    List<Ingredient> merge(List<Ingredient> list1, List<Ingredient> list2);
}
