import aoc.*
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class InverseCaptchaTest : FeatureSpec() {

    init {
        feature("Day 1: Inverse Captcha") {

            val input = read("input1")

            scenario("Part 1") {
                captcha("1122") shouldBe 3
                captcha("1111") shouldBe 4
                captcha("1234") shouldBe 0
                captcha("91212129") shouldBe 9

                println(captcha(input))
            }

            scenario("Part 2") {
                captcha("1212", 2) shouldBe 6
                captcha("1221", 2) shouldBe 0
                captcha("123425", 3) shouldBe 4
                captcha("123123", 3) shouldBe 12
                captcha("12131415", 4) shouldBe 4

                println(captcha(input, input.length / 2))
            }
        }
    }
}
