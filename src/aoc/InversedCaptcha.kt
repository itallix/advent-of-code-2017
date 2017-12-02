// http://adventofcode.com/2017/day/1

package aoc

fun main(args: Array<String>) {
    val seq = read("input1")
    println(captcha(seq, 1)) // part 1
    println(captcha(seq, seq.length / 2)) // part 2
}

fun read(name: String): String = ClassLoader.getSystemClassLoader().getResource(name).readText().trim()

private fun captcha(seq: String, step: Int): Int =
        seq.filterIndexed { index, value -> value == seq[(index + step) % seq.length] }.map { Character.getNumericValue(it) }.sum()
