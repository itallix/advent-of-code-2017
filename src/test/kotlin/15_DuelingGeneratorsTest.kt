import aoc.generator
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class DuelingGeneratorsTest : FeatureSpec() {

    init {
        feature("Day 15: Dueling Generators") {

            val a: Long = 783
            val b: Long = 325

            scenario("Part 1") {
                generator(65, 8921) shouldBe 588
                println(generator(a, b))
            }

            scenario("Part 2") {
                generator(65, 8921, Pair(4, 8), 5_000_000) shouldBe 309
                println(generator(a, b, Pair(4, 8), 5_000_000))
            }
        }
    }
}
