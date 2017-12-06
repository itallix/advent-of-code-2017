package aoc

fun main(args: Array<String>) {
    val bank = read("input_test").split("\t").map { it.toInt() }.toList()
    val banks = arrayListOf<List<Int>>()
    val next = bank.toMutableList()
    var steps = 0
    while (!banks.contains(next)) {
        banks.add(next.toList())
        var redistribute = next.max()?:0
        var index = next.indexOf(redistribute)
        next[index++] = 0
        while (redistribute-- != 0) {
            next[index % next.size] = next[index++ % next.size] + 1
        }
        steps++
    }
    println(steps) // part 1
    println(steps - banks.indexOfLast { it == next }) // part 2
}