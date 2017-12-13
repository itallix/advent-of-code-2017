package aoc

class PacketScanner(input: String) {

    private val layout: Map<Int, Int>

    init {
        this.layout = input.split("\n").map {
            val line = it.split(": ")
            Integer.parseInt(line[0]) to Integer.parseInt(line[1])
        }.toMap()
    }

    fun severity(delay: Int = 0): Int {
        var iterations = delay
        var severity = 0
        (0..layout.keys.max()!!).forEach {
            val depth = layout[it] ?: 0
            val layerPos = iterations++ % (2 * depth - 2)
            if (layerPos == 0) severity += it * (layout[it] ?: 0)
        }
        return severity
    }

    fun delay(): Int {
        var severity: Int
        var delay = 1
        while (true) {
            severity = severity(delay)
            if (severity == 0 && (delay % (2 * (layout[0] ?: 0) - 2) != 0)) break
            delay++
        }
        return delay
    }
}