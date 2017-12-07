import aoc.checksum1
import aoc.checksum2
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class CorruptionChecksumTest : FeatureSpec() {
    init {
        feature("Day 2: Corruption Checksum") {

            fun parse(input: String, delimeter: String = "\t"): List<List<Int>> =
                    input.split(System.getProperty("line.separator"))
                            .map { it.split(delimeter).map { it.toInt() } }
                            .toList()

            val input = parse(read("input2"))

            scenario("Part 1") {
                val data = parse("5 1 9 5\n" +
                        "7 5 3\n" +
                        "2 4 6 8", " ")
                checksum1(data) shouldBe 18

                println(checksum1(input))
            }

            scenario("Part 2") {
                val data = parse("5 9 2 8\n" +
                        "9 4 7 3\n" +
                        "3 8 6 5", " ")
                checksum2(data) shouldBe 9

                println(checksum2(input))
            }
        }
    }
}