import aoc.*
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class TwistyTrampolinesTest : FeatureSpec() {

    init {
        feature("Day 5: Twisty Trampolines") {

            fun parse(input: String): IntArray = input.split(System.getProperty("line.separator")).map { it.toInt() }.toIntArray()

            val input = parse(read("input5"))

            scenario("Part 1") {
                calcSteps(parse("0\n" +
                        "3\n" +
                        "0\n" +
                        "1\n" +
                        "-3")) shouldBe 5

                println(calcSteps(input))
            }

            scenario("Part 2") {
                calcSteps(parse("0\n" +
                        "3\n" +
                        "0\n" +
                        "1\n" +
                        "-3"), step) shouldBe 10

                println(calcSteps(input, step))
            }
        }
    }
}
