// http://adventofcode.com/2017/day/2

package aoc

val VALUE_DELIMETER = "\t"

fun main(args: Array<String>) {
    val lines = read("input2").split(System.getProperty("line.separator"))
    println(part1(lines))
    println(part2(lines))
}

private fun part1(lines: List<String>): Int {
    return lines.fold(0, { total, line ->
        val numbers = line.split(VALUE_DELIMETER).map { it.toInt() }
        val max = numbers.max()?:0
        val min = numbers.min()?:0
        total + max - min
    })
}

private fun part2(lines: List<String>): Int {
    return lines.fold(0, { total, line ->
        val numbers = line.split(VALUE_DELIMETER).map { it.toInt() }
        var even = 0
        loop@ for (i in 0 until numbers.size) {
            for (j in 0 until numbers.size) {
                if (numbers[i] != numbers[j]) {
                    if (numbers[i] % numbers[j] == 0) {
                        even += (numbers[i] / numbers[j])
                        break@loop
                    }
                }
            }
        }
        total + even
    })
}