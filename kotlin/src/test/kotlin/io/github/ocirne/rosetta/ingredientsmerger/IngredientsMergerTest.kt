package io.github.ocirne.rosetta.ingredientsmerger

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.reflect.KClass

internal class IngredientsMergerTest {

    @ParameterizedTest
    @MethodSource("ingredientsMergerVariants")
    fun `can merge ingredients`(ingredientsMergerClass: KClass<out IngredientsMerger>) {
        val ingredientsMerger = ingredientsMergerClass.constructors.first().call()
        val list1 = listOf(Ingredient("apple", 3), Ingredient("banana", 1))
        val list2 = listOf(Ingredient("apple", 5), Ingredient("pear", 1))
        val result = ingredientsMerger.merge(list1, list2)
        val apples = result.find { it.name == "apple" }
        apples shouldNotBe null
        apples!!.amount shouldBe 8
    }

    companion object {

        @JvmStatic
        fun ingredientsMergerVariants(): Stream<Arguments> =
            Stream.of(
                Arguments.of(IngredientsMergerDefault::class),
                Arguments.of(IngredientsMergerExtension::class)
            )
    }
}
