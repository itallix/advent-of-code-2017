import aoc.*
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class HighEntropyPassphrasesTest : FeatureSpec() {

    init {
        feature("Day 4: High Entropy Passphrases") {

            fun parse(input: String): List<List<String>> = input.split(System.getProperty("line.separator"))
                    .map { it.split(" ") }
                    .toList()

            val input = parse(read("input4"))

            scenario("Part 1") {
                calcValidPasses(parse("aa bb cc dd ee")) shouldBe 1
                calcValidPasses(parse("aa bb cc dd aa")) shouldBe 0
                calcValidPasses(parse("aa bb cc dd aaa")) shouldBe 1

                println(calcValidPasses(input))
            }

            scenario("Part 2") {
                calcValidPasses(parse("abcde fghij")) shouldBe 1
                calcValidPasses(parse("abcde xyz ecdab")) shouldBe 0
                calcValidPasses(parse("a ab abc abd abf abj")) shouldBe 1
                calcValidPasses(parse("iiii oiii ooii oooi oooo")) shouldBe 1
                calcValidPasses(parse("oiii ioii iioi iiio")) shouldBe 0

                println(calcValidPassesSecured(input))
            }
        }
    }
}
