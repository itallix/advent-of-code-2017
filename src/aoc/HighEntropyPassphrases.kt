// http://adventofcode.com/2017/day/4

package aoc

fun main(args: Array<String>) {
    val lines = read("input4").split(System.getProperty("line.separator"))
            .map { it.split(" ") }
            .toList()

    println(calcValidPasses(lines)) // part 1

    val sorted = lines.map { it.map { w -> w.toCharArray().sorted().joinToString("") } }
    println(calcValidPasses(sorted)) // part 2
}

private fun calcValidPasses(ll: List<List<String>>): Int =
        ll.map { it.toSet() }.filterIndexed { i, s -> s.size == ll[i].size }.size
