import aoc.Stream
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class StreamProcessingTest : FeatureSpec() {

    init {
        feature("Day 9: Stream Processing") {

            val stream = Stream(read("input9"))

            scenario("Part 1") {
                Stream("{}").sum() shouldBe 1
                Stream("{{{}}}").sum() shouldBe 6
                Stream("{{}, {}}").sum() shouldBe 5
                Stream("{{{},{},{{}}}}").sum() shouldBe 16
                Stream("{<a>,<a>,<a>,<a>}").sum() shouldBe 1
                Stream("{{<ab>},{<ab>},{<ab>},{<ab>}}").sum() shouldBe 9
                Stream("{{<!!>},{<!!>},{<!!>},{<!!>}}").sum() shouldBe 9
                Stream("{{<a!>},{<a!>},{<a!>},{<ab>}}").sum() shouldBe 3

                println(stream.sum())
            }

            scenario("Part 2") {
                Stream("<>").symbols() shouldBe 0
                Stream("<random characters>").symbols() shouldBe 17
                Stream("<<<<>").symbols() shouldBe 3
                Stream("<{!>}>").symbols() shouldBe 2
                Stream("<!!>").symbols() shouldBe 0
                Stream("<!!!>>").symbols() shouldBe 0
                Stream("<{o\"i!a,<{i<a>").symbols() shouldBe 10

                println(stream.symbols())
            }
        }
    }
}
