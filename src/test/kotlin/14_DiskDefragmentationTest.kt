import aoc.DiskDefragmenter
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class DiskDefragmentationTest : FeatureSpec() {

    init {
        feature("Day 14: Disk Defragmentation") {

            val input = "amgozmfv"

            scenario("Part 1") {
                DiskDefragmenter("flqrgnkx").digits shouldBe 8108

                println(DiskDefragmenter(input).digits)
            }

            scenario("Part 2") {
                DiskDefragmenter("flqrgnkx").connected() shouldBe 1242

                println(DiskDefragmenter("amgozmfv").connected())
            }
        }
    }
}
