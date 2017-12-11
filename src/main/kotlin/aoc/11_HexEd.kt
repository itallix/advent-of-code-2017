package aoc

class HexEd(var distance: Int = 0, var max: Int = 0) {

    constructor(input: String) : this() {
        val steps = input.split(",")
        var x = 0
        var y = 0
        steps.forEach {
            when (it) {
                "n" -> y++
                "s" -> y--
                "ne" -> x++
                "sw" -> x--
                "nw" -> { x--; y++ }
                "se" -> { x++; y-- }
                else -> { }
            }
            distance = listOf(Math.abs(x), Math.abs(y), Math.abs(0 - x - y)).max()!!
            max = Math.max(max, distance)
        }
    }
}