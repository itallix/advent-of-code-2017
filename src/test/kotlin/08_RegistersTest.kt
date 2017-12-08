import aoc.Condition
import aoc.Instruction
import aoc.process
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class RegistersTest : FeatureSpec() {

    init {
        feature("Day 8: Registers") {

            fun parse(input: String): List<Instruction> = input.split(System.getProperty("line.separator"))
                    .map { l ->
                        val a = l.split(" if ")
                        val left = a[0].split(" ")
                        val right = a[1].split(" ")
                        Instruction(left[0], left[1], left[2].toInt(), Condition(right[0], right[1], right[2].toInt()))
                    }.toList()

            val input = parse(read("input8"))

            scenario("Part 1") {
                process(parse("b inc 5 if a > 1\n" +
                        "a inc 1 if b < 5\n" +
                        "c dec -10 if a >= 1\n" +
                        "c inc -20 if c == 10")).first shouldBe 1

                println(process(input).first)
            }

            scenario("Part 2") {
                process(parse("b inc 5 if a > 1\n" +
                        "a inc 1 if b < 5\n" +
                        "c dec -10 if a >= 1\n" +
                        "c inc -20 if c == 10")).second shouldBe 10

                println(process(input).second)
            }
        }
    }
}
