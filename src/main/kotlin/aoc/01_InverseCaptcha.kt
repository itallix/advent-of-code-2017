// http://adventofcode.com/2017/day/1

package aoc

fun captcha(seq: String, step: Int = 1): Int =
        seq.filterIndexed { index, value -> value == seq[(index + step) % seq.length] }
                .map { Character.getNumericValue(it) }.sum()
