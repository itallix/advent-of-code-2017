package aoc

import java.util.*

class DiskDefragmenter(val input: String) {

    val digits: Int
    private val area: HashMap<Int, String> = HashMap()

    init {
        this.digits = (0..127).map {
            var row = ""
            val digits = Knot("$input-$it").hash().map {
                val decimal = Integer.parseInt("$it", 16)
                val binary = Integer.toBinaryString(decimal)
                row += binary.padStart(4, '0')
                binary.filter { it == '1' }.length
            }.sum()
            area.put(it, row)
            digits
        }.sum()
    }

    fun connected(): Int {
        val visited = Array(128, { BooleanArray(128, { false }) })

        fun search(x: Int, y: Int) {
            if (x < 0 || y < 0 || x > 127 || y > 127) return
            if (visited[x][y] || area[x]!![y] == '0') return
            visited[x][y] = true
            search(x - 1, y)
            search(x + 1, y)
            search(x, y - 1)
            search(x, y + 1)
        }

        var groups = 0
        (0..127).forEach { x ->
            (0..127).forEach { y ->
                if (!visited[x][y] && area[x]!![y] == '1') {
                    groups++
                    search(x, y)
                }
            }
        }
        return groups
    }
}
