import aoc.calcFirstLargerValue
import aoc.calcManhattanDistance
import aoc.generateSpiralMemory
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class SpiralMemoryTest : FeatureSpec() {

    init {
        feature("Day 3: Spiral Memory") {

            val input = 289326
            val spiralMemory = generateSpiralMemory(input)

            scenario("Part 1") {
                listOf(Pair(1, 0), Pair(12, 3), Pair(23, 2), Pair(1024, 31)).forEach {
                    (t, a) -> calcManhattanDistance(spiralMemory, t) shouldBe a
                }

                println("Number of steps: ${calcManhattanDistance(spiralMemory, input)}")
            }

            scenario("Part 2") {
                println("First larger value: ${calcFirstLargerValue(spiralMemory, input)}")
            }
        }
    }

}

