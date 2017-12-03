// http://adventofcode.com/2017/day/2

package aoc

fun main(args: Array<String>) {
    val lines = read("input2").split(System.getProperty("line.separator"))
            .map { it.split("\t").map { it.toInt() } }
            .toList()
    println(lines.map { it.sorted() }.map { it.last() - it.first() }.sum())
    println(lines.map { l ->
        l.flatMap { x -> l.map { y -> if (x % y == 0) x / y else 0 } }.filter { it > 1 }.sum()
    }.sum())
}