package io.github.ocirne.rosetta.ingredientsmerger.given

class Ingredient(val name: String, val amount: Int = 0) {

    override fun toString(): String {
        return "Ingredient($name: $amount)"
    }
}
