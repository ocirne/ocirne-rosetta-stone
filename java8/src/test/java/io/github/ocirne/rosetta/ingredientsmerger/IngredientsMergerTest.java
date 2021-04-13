package io.github.ocirne.rosetta.ingredientsmerger;

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class IngredientsMergerTest {

    @ParameterizedTest
    @MethodSource("ingredientsMergerVariants")
    public void canMergeIngredients(IngredientsMerger ingredientsMerger) {
        List<Ingredient> list1 = Arrays.asList(new Ingredient("apple", 3), new Ingredient ("banana", 1));
        List<Ingredient> list2 = Arrays.asList(new Ingredient("apple", 5), new Ingredient ("pear", 1));

        List<Ingredient> list = ingredientsMerger.merge(list1, list2);
        Optional<Ingredient> apples = list.stream().filter(ingr -> ingr.getName().equals("apple")).findFirst();
        if (!apples.isPresent())
            throw new AssertionError("es müssen apples vorhanden sein");
        if (apples.get().getAmount() != 8)
            throw new AssertionError("es müssen 8 apples vorhanden sein");
    }

    private static Stream<Arguments> ingredientsMergerVariants() {
        return Stream.of(
                Arguments.of(new IngredientsMergerStreams()),
                Arguments.of(new IngredientsMergerProcedural())
        );
    }
}