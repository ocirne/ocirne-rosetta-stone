package io.github.ocirne.rosetta.ingredientsmerger;

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IngredientsMergerTest {

    @Test
    public void canMergeIngredients() {
        List<Ingredient> list1 = Arrays.asList(new Ingredient("apple", 3), new Ingredient ("banana", 1));
        List<Ingredient> list2 = Arrays.asList(new Ingredient("apple", 5), new Ingredient ("pear", 1));

        List<Ingredient> list = IngredientsMerger.merge(list1, list2);
        Optional<Ingredient> apples = list.stream().filter(ingr -> ingr.getName().equals("apple")).findFirst();
        if (!apples.isPresent())
            throw new AssertionError("es müssen apples vorhanden sein");
        if (apples.get().getAmount() != 8)
            throw new AssertionError("es müssen 8 apples vorhanden sein");
    }
}