// http://adventofcode.com/2017/day/4

package aoc

fun calcValidPasses(ll: List<List<String>>): Int =
        ll.map { it.toSet() }.filterIndexed { i, s -> s.size == ll[i].size }.size

fun calcValidPassesSecured(ll: List<List<String>>): Int {
    val sorted = ll.map { it.map { w -> w.toCharArray().sorted().joinToString("") } }
    return calcValidPasses(sorted)
}
