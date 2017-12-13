// graphs implementation taken from https://github.com/dkandalov/kotlin-99

import graphs.Graph
import graphs.components
import graphs.toGraph
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class DigitalPlumberTest : FeatureSpec() {

    init {
        feature("Day 12: Digital Plumber") {

            val input = read("input12")

            fun parse(input: String): Graph<String, Nothing> {
                val lines = input.split("\n").map { it.split(" <-> ") }
                val nodes = lines.map { it[0] }
                val adjacent = lines.map { it[1].split(", ") }
                val list = nodes.mapIndexed{ index, node -> adjacent[index].map { "$node-$it" } }
                return list.map { it.joinToString() }.toString().toGraph()
            }

            val components = parse(input).components()

            scenario("Part 1") {
                parse("0 <-> 2\n" +
                        "1 <-> 1\n" +
                        "2 <-> 0, 3, 4\n" +
                        "3 <-> 2, 4\n" +
                        "4 <-> 2, 3, 6\n" +
                        "5 <-> 6\n" +
                        "6 <-> 4, 5").components().filter { it.nodes.containsKey("0") }[0].nodes.size shouldBe 6

                println(components.filter { it.nodes.containsKey("0") }[0].nodes.size)
            }

            scenario("Part 2") {
                parse("0 <-> 2\n" +
                        "1 <-> 1\n" +
                        "2 <-> 0, 3, 4\n" +
                        "3 <-> 2, 4\n" +
                        "4 <-> 2, 3, 6\n" +
                        "5 <-> 6\n" +
                        "6 <-> 4, 5").components().size shouldBe 2

                println(components.size)
            }
        }
    }
}
