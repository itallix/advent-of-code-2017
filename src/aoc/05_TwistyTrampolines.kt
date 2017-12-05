package aoc

fun main(args: Array<String>) {
    val lines = read("input5").split(System.getProperty("line.separator"))
            .map { it.toInt() }.toIntArray()
    println("Part One: ${calcSteps(lines, { it + 1 })}")
    println("Part Two: ${calcSteps(lines, { if (it >= 3) it - 1 else it + 1 })}")
}

private fun calcSteps(lines: IntArray, step: (Int) -> Int): Int {
    val array = lines.copyOf()
    var index = 0
    var steps = 0
    while (index < array.size) {
        val delta = array[index]
        array[index] = step(delta)
        index += delta
        steps++
    }
    return steps
}