import io.kotlintest.specs.FeatureSpec

class SpinlockTest : FeatureSpec() {

    init {
        feature("Day 17: Spinlock") {

            val step = 386

            scenario("Part 1") {
                val buff = mutableListOf(0)
                var cur = 0
                (1..2018).forEach {
                    cur = (cur + step) % buff.size + 1
                    buff.add(cur, it)
                }

                println(buff[(buff.indexOf(2017) + 1) % buff.size])
            }

            scenario("Part 2") {
                var cur = 0
                var after0 = 0
                (1..50_000_000 + 1).forEach {
                    cur = (cur + step) % it + 1
                    if (cur == 1) { after0 = it }
                }

                println(after0)
            }
        }
    }
}
