// http://adventofcode.com/2017/day/2

package aoc

fun checksum1(lines: List<List<Int>>): Int = lines.map { it.sorted() }.map { it.last() - it.first() }.sum()

fun checksum2(lines: List<List<Int>>): Int {
    return lines.map { l ->
        l.flatMap { x -> l.map { y -> if (x % y == 0) x / y else 0 } }.filter { it > 1 }.sum()
    }.sum()
}