import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class SolutionTest : FeatureSpec() {

    init {
        feature("Day 8:") {

            fun part1() {}

            fun part2() {}

            fun parse(input: String): String = input

            val input = parse(read("input8"))

            scenario("Part 1") {
                part1() shouldBe 0

                println(part1())
            }

            scenario("Part 2") {
                part2() shouldBe 0

                println(part2())
            }
        }
    }
}
