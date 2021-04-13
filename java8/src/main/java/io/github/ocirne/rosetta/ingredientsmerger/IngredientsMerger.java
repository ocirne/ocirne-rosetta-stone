package io.github.ocirne.rosetta.ingredientsmerger;

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingInt;

class IngredientsMerger {

    public static List<Ingredient> merge(List<Ingredient> list1, List<Ingredient> list2) {
        // Aufgabe 1. Verwendung von Streams, groupBy und dann den Amount auf-addieren
        return Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.groupingBy(Ingredient::getName, summingInt(Ingredient::getAmount)))
                .entrySet().stream()
                .map(entry -> new Ingredient(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
