package aoc

fun generateNext(value: Long, factor: Int, divider: Int): Long {
    var newValue = value
    while (true) {
        newValue = newValue * factor % 2147483647
        if (newValue % divider == 0L) return newValue
    }
}

fun generator(a: Long, b: Long, divider: Pair<Int, Int> = Pair(1, 1), iterations: Int = 40_000_000): Int {
    val factorA = 16807
    val factorB = 48271

    var matches = 0
    var nextA: Long = a
    var nextB: Long = b
    (0..iterations).forEach {
        nextA = generateNext(nextA, factorA, divider.first)
        nextB = generateNext(nextB, factorB, divider.second)

        if (nextA.toShort() == nextB.toShort()) matches++
    }
    return matches
}
