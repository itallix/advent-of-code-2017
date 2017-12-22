import aoc.SportificaVirus
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class SporificaVirusTest : FeatureSpec() {

    init {
        feature("Day 22: Sporifica Virus") {

            val input = read("input22")
            val test = (". . . . . . . . .\n" +
                    ". . . . . . . . .\n" +
                    ". . . . . . . . .\n" +
                    ". . . . . # . . .\n" +
                    ". . . # . . . . .\n" +
                    ". . . . . . . . .\n" +
                    ". . . . . . . . .\n" +
                    ". . . . . . . . .").replace(" ", "")
            
            scenario("Part 1") {
                
                SportificaVirus(test).infection(7) shouldBe 5
                SportificaVirus(test).infection(70) shouldBe 41
                SportificaVirus(test).infection(10_000) shouldBe 5587

                println(SportificaVirus(input).infection(10_000))
            }

            scenario("Part 2") {
                SportificaVirus(test).evolved(100) shouldBe 26
                SportificaVirus(test).evolved(10_000_000) shouldBe 2511944
                
                println(SportificaVirus(input).evolved(10_000_000))
            }
        }
    }
}
