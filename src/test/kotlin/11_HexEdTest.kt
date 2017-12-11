import aoc.HexEd
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class HexEdTest : FeatureSpec() {

    init {
        feature("Day 11: Hex Ed") {

            val input = read("input11")

            val hexEd = HexEd(input)

            scenario("Part 1") {
                HexEd("ne,ne,ne").distance shouldBe 3
                HexEd("ne,ne,sw,sw").distance shouldBe 0
                HexEd("ne,ne,s,s").distance shouldBe 2
                HexEd("se,sw,se,sw,sw").distance shouldBe 3

                println(hexEd.distance)
            }

            scenario("Part 2") {
                println(hexEd.max)
            }
        }
    }
}
