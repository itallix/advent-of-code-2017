// http://adventofcode.com/2017/day/3

package aoc

import java.lang.Math.abs
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    val input = 289326
    val spiralMemory = generateSpiralMemory(input)
    tests1(spiralMemory)
    println("Number of steps: ${calcManhattanDistance(spiralMemory, input)}") // Part1
    println("First larger value: ${calcFirstLargerValue(spiralMemory, input)}") // Part2
}

private fun calcFirstLargerValue(circular: List<Pair<Int, Int>>, target: Int): Int {
    val neighbors = -1..1
    val values = arrayListOf(1)
    circular.drop(1).takeWhile { (x, y) ->
        val sum = neighbors.flatMap { r ->
            neighbors.map { c ->
                values.getOrElse(circular.indexOf(Pair(x + c, y + r))) { 0 }
            }
        }.sum()
        values.add(sum)
        sum < target
    }
    return values.last()
}

private fun calcManhattanDistance(circular: List<Pair<Int, Int>>, target: Int): Int {
    val (x, y) = circular[target - 1]
    return abs(x) + abs(y)
}

private fun generateSpiralMemory(target: Int): List<Pair<Int, Int>> {
    val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    val points = mutableListOf(Pair(0, 0))
    var offset = 0
    var steps = 1

    while (points.size < target) {
        (0..1).forEach { // spiral turn by 2 sides with the same amount of steps
            (0 until steps).forEach {
                val (x, y) = points.last()
                val (dx, dy) = directions[offset]
                points.add(Pair(x + dx, y + dy))
            }
            offset = (offset + 1) % 4
        }
        steps++
    }
    return points
}

private fun tests1(spiralMemory: List<Pair<Int, Int>>) {
    listOf(Pair(1, 0), Pair(12, 3), Pair(23, 2), Pair(1024, 31)).forEach {
        (t, a) -> assertEquals(calcManhattanDistance(spiralMemory, t), a)
    }
}