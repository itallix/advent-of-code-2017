import aoc.calcBankSteps
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class MemoryReallocationTest : FeatureSpec() {
    init {
        feature("Day 6: Memory Reallocation") {

            fun parse(input: String): List<Int> = input.split("\t").map { it.toInt() }.toList()

            val input = parse(read("input6"))

            scenario("Part 1") {
                calcBankSteps(listOf(0, 2, 7, 0)).first shouldBe 5

                println(calcBankSteps(input).first)
            }

            scenario("Part 2") {
                calcBankSteps(listOf(0, 2, 7, 0)).second shouldBe 4

                println(calcBankSteps(input).second)
            }
        }
    }
}