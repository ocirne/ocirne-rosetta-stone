package io.github.ocirne.rosetta.projecteuler

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class Problem8Test {

    @Test
    fun `can solve problem 8 with example`() {
        Problem8().solve(4) shouldBe 5832
    }
}
