import aoc.Knot
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class KnotHashTest : FeatureSpec() {

    init {
        feature("Day 10: Knot Hash") {

            val input = "88,88,211,106,141,1,78,254,2,111,77,255,90,0,54,205"

            scenario("Part 1") {
                Knot("3,4,1,5").multi((0..4).toMutableList()) shouldBe 12

                println(Knot(input).multi((0..255).toMutableList()))
            }

            scenario("Part 2") {
                Knot("").hash() shouldBe "a2582a3a0e66e6e86e3812dcb672a272"
                Knot("AoC 2017").hash() shouldBe "33efeb34ea91902bb2f59c9920caa6cd"
                Knot("1,2,3").hash() shouldBe "3efbe78a8d82f29979031a4aa0b16a9d"
                Knot("1,2,4").hash() shouldBe "63960835bcdc130f0b66d7ff4f6a5a8e"

                println(Knot(input).hash())
            }
        }
    }
}
