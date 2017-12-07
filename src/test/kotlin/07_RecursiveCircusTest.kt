import aoc.Tower
import aoc.detectUnbalanced
import aoc.findRoot
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class SolutionTest : FeatureSpec() {
    init {
        feature("Day 7") {

            fun parse(input: String): List<Tower> {
                val towers = arrayListOf<Tower>()
                input.split(System.getProperty("line.separator"))
                        .map { it.split("-> ") }
                        .forEach { l ->
                            val key = l[0].split(" ")
                            val values = if (l.size > 1) {
                                l[1].split(", ").toList()
                            } else listOf()
                            val weight = if (key[1].length > 2) {
                                key[1].subSequence(1, key[1].length - 1).toString().toInt()
                            } else 0

                            towers.add(Tower(key[0], arrayListOf(), values, weight))
                        }
                return towers
            }

            val data = parse("pbga (66)\n" +
                    "xhth (57)\n" +
                    "ebii (61)\n" +
                    "havc (66)\n" +
                    "ktlj (57)\n" +
                    "fwft (72) -> ktlj, cntj, xhth\n" +
                    "qoyq (66)\n" +
                    "padx (45) -> pbga, havc, qoyq\n" +
                    "tknk (41) -> ugml, padx, fwft\n" +
                    "jptl (61)\n" +
                    "ugml (68) -> gyxo, ebii, jptl\n" +
                    "gyxo (61)\n" +
                    "cntj (57)")
            val input = parse(read("input7"))

            scenario("Part 1") {
                findRoot(data)?.name shouldBe "tknk"

                println(findRoot(input)?.name) //ahnofa
            }

            scenario("Part 2") {
//                detectUnbalanced(findRoot(data)!!) //243

                detectUnbalanced(findRoot(input)!!) //802
            }
        }
    }
}