package aoc

fun calcSteps(lines: IntArray, step: (Int) -> Int = { it + 1 } ) : Int {
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

val step: (Int) -> Int = { if (it >= 3) it - 1 else it + 1 }