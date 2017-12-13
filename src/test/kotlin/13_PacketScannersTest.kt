import aoc.PacketScanner
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class PacketScannersTest : FeatureSpec() {

    init {
        feature("Day 13: Packet Scanners") {

            val inputScanner = PacketScanner(read("input13"))

            scenario("Part 1") {
                PacketScanner("0: 3\n" +
                        "1: 2\n" +
                        "4: 4\n" +
                        "6: 4").severity() shouldBe 24

                println(inputScanner.severity())
            }

            scenario("Part 2") {
                PacketScanner("0: 3\n" +
                        "1: 2\n" +
                        "4: 4\n" +
                        "6: 4").delay() shouldBe 10

                print(inputScanner.delay())
            }
        }
    }
}
