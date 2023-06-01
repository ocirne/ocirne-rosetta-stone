package io.github.ocirne.rosetta.projecteuler

class Problem8 {

    fun solve(length: Int): Long {
        return (0..content.length - length).maxOf { i ->
            content.substring(i, i + length)
                .toList()
                .map { c -> c.digitToInt().toLong() }
                .reduce { a, b -> a * b }
        }
    }
    companion object {
        val content = this::class.java.classLoader
            .getResourceAsStream("pe8.txt")!!
            .bufferedReader()
            .readLines()
            .joinToString("") { it.trim() }
    }
}

fun main() {
    println(Problem8().solve(13))
}
