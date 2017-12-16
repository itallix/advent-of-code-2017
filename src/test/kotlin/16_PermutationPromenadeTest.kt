import aoc.dance
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class PermutationPromenadeTest : FeatureSpec() {

    init {
        feature("Day 16: Permutation Promenade") {

            val input = ('a'..'p').joinToString("")
            val steps = read("input16").split(",")

            scenario("Part 1") {
                dance("abcde", "s1,x3/4,pe/b".split(",")) shouldBe "baedc"

                println(dance(input, steps))
            }

            scenario("Part 2") {
                println(dance(input, steps, 1_000_000_000))
            }
        }
    }
}
