package aoc

fun MutableList<Int>.reverse(length: Int, current: Int): List<Int> {
    val tmp = arrayListOf<Int>()
    var curLength = length
    var i = current % this.size
    while (curLength > 0) {
        tmp.add(this[i++ % this.size])
        curLength--
    }
    var j = current % this.size
    tmp.reversed().forEach { this[j++ % this.size] = it }
    return this
}

class Knot(val input: String) {

    fun multi(source: MutableList<Int>): Int {
        val lengths = input.split(",").map { it.toInt() }.toList()

        var current = 0
        var skipSize = 0
        lengths.forEach { source.reverse(it, current); current += it + skipSize++ }
        return source[0] * source[1]
    }

    fun hash(source: MutableList<Int> = (0..255).toMutableList()): String {
        val suffix = listOf(17, 31, 73, 47, 23)
        val lengths = input.toCharArray().map { it.toInt() }.toMutableList() + suffix

        var current = 0
        var skipSize = 0
        (1..64).forEach { lengths.forEach { source.reverse(it, current); current += it + skipSize++ } }

        val dense = source.chunked(16).map { it.reduce { a, b -> a xor b } }
        return dense.joinToString("") {
            val hex = Integer.toHexString(it); if (hex.length == 1) "0$hex" else hex
        }
    }
}
