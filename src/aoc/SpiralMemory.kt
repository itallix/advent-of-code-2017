// http://adventofcode.com/2017/day/3

package aoc

import aoc.Direction.*
import kotlin.test.assertEquals

enum class Direction { EAST, NORTH, WEST, SOUTH }

fun main(args: Array<String>) {
    val input = 289326
    val spiralMemory = generateSpiralMemory(input)
    tests1(spiralMemory)
    println("Number of steps: ${calcManhattanDistance(spiralMemory, input)}") // Part1
    println("First larger value: ${getNextValue(spiralMemory, input)}") // Part2
}

private fun getNextValue(circular: List<Pair<Int, Int>>, target: Int): Int? {
    val values = arrayListOf(1)
    val neighbors = -1..1
    for (pair in circular.drop(1)) {
        var sum = 0
        for (row in neighbors) {
            for (col in neighbors) {
                sum += values.getOrElse(circular.indexOf(Pair(pair.first + col, pair.second + row))) { 0 }
            }
        }
        if (sum > target) return sum else values.add(sum)
    }
    return null
}

private fun calcManhattanDistance(circular: List<Pair<Int, Int>>, target: Int): Int {
    val coordinates = circular[target - 1]
    return Math.abs(coordinates.first) + Math.abs(coordinates.second)
}

private fun generateSpiralMemory(input: Int): List<Pair<Int, Int>> {
    var dir = EAST
    var i = 1
    var iteration = 0
    var length = 1
    var isFirstIterationInDirection = false

    val pairs = arrayListOf(Pair(0, 0))

    while (i < input) {

        val last = pairs.last()
        val x = last.first
        val y = last.second
        pairs.add(when (dir) {
            EAST -> Pair(x + 1, y)
            NORTH -> Pair(x, y + 1)
            WEST -> Pair(x - 1, y)
            SOUTH -> Pair(x, y - 1)
        })

        iteration++
        i++

        if (iteration == length) {
            isFirstIterationInDirection = if (isFirstIterationInDirection) {
                length++
                false
            } else true
            dir = Direction.values()[(dir.ordinal + 1) % Direction.values().size]
            iteration = 0
        }
    }
    return pairs
}

private fun tests1(spiralMemory: List<Pair<Int, Int>>) {
    assertEquals(calcManhattanDistance(spiralMemory, 1), 0)
    assertEquals(calcManhattanDistance(spiralMemory, 12), 3)
    assertEquals(calcManhattanDistance(spiralMemory, 23), 2)
    assertEquals(calcManhattanDistance(spiralMemory, 1024), 31)
}