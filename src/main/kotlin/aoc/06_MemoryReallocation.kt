package aoc

fun calcBankSteps(bank: List<Int>): Pair<Int, Int> {
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
    return Pair(steps, steps - banks.indexOfLast { it == next })
}