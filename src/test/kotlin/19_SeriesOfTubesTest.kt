import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class SeriesOfTubesTest : FeatureSpec() {

    init {
        feature("Day 19: A Series of Tubes") {

            val input = ClassLoader.getSystemClassLoader().getResource("input19").readText()

            fun collectLetters(input: String): Pair<String, Int> {
                val maze = input.split("\n")
                var x = maze[0].indexOf('|')
                var y = 0
                var dir = 'D'
                var steps = 0
                var letters = ""

                var current = '|'
                while (current != ' ') {
                    steps++
                    when (dir) {
                        'D' -> y++
                        'U' -> y--
                        'R' -> x++
                        'L' -> x--
                    }
                    current = maze[y][x]
                    if (current == '+') {
                        when (dir) {
                            'U', 'D' -> dir = if (maze[y][x - 1] != ' ') 'L' else 'R'
                            'L', 'R' -> dir = if (maze[y - 1][x] != ' ') 'U' else 'D'
                        }
                    } else {
                        if (current != '|' && current != '-' && current != ' ') {
                            letters += current
                        }
                    }
                }
                return Pair(letters, steps)
            }

            val test = """     |          
     |  +--+    
     A  |  C    
 F---|--|-E---+ 
     |  |  |  D 
     +B-+  +--+ 
"""

            scenario("Part 1") {
                collectLetters(test).first shouldBe "ABCDEF"

                println(collectLetters(input).first)
            }

            scenario("Part 2") {
                collectLetters(test).second shouldBe 38

                println(collectLetters(input).second)
            }
        }
    }
}
