import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class FractalArtTest : FeatureSpec() {

    init {
        feature("Day 21: Fractal Art") {

            val input = read("input21")

            fun List<List<String>>.transpose() = get(0).indices.map { col -> map { it[col] } }

            fun process(grid: List<String>, rules: Map<List<String>, List<String>>): List<String> {
                val size = if (grid.size % 2 == 0) 2 else 3
                val chunks = grid.chunked(size) { c -> c.map { it.chunked(size) }.transpose() }
                        .map { r -> r.map { rules[it]!! } }
                return chunks.flatMap { r -> r.transpose().map { it.joinToString("") } }
            }

            fun enhanced(pattern: List<String>): List<List<String>> {
                val rotated = generateSequence(pattern, { l -> l.indices.map { i ->
                    l.indices.reversed().map { j ->
                        l[j][i]
                    }.joinToString("")
                }}).take(4).asIterable()
                return rotated + rotated.map { it.reversed() }
            }

            fun pixelsOn(input: String, n: Int): Int {
                val rules = input.split("\n").map {
                    it.split(" => ")
                }.associate { (k, v) -> k.split("/") to v.split("/") }

                val all = rules.flatMap { e -> enhanced(e.key).map { it to e.value } }.toMap()
                var grid = ".#./..#/###".split('/')
                repeat(n) { grid = process(grid, all) }

                return grid.sumBy { it.count { it == '#' } }
            }

            scenario("Part 1") {
                val test = ("../.# => ##./#../...\n" +
                        ".#./..#/### => #..#/..../..../#..#")
                pixelsOn(test, 2) shouldBe 12

                println(pixelsOn(input, 5))
            }

            scenario("Part 2") {
                println(pixelsOn(input, 18))
            }
        }
    }
}
