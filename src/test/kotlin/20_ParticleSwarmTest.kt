import io.kotlintest.specs.FeatureSpec


class ParticleSwarmTest : FeatureSpec() {

    init {
        feature("Day 20: Particle Swarm") {

            val input = read("input20")
            
            data class Particle(val p: Array<Long>, val v: Array<Long>, val a: Array<Long>) {
                fun tick() = (0..2).forEach { v[it] += a[it]; p[it] += v[it] }
                fun distance(): Long = p.map { Math.abs(it) }.sum()
                fun position(): String = "${p[0]}, ${p[1]}, ${p[2]}"
            }

            val particles: List<Particle> = input.splitToSequence(",", "\n")
                    .map { it.filter { it in "-0123456789" }.toLong() }
                    .chunked(3) { c -> Array(3, { c[it] }) }
                    .chunked(3) { Particle(it[0], it[1], it[2]) }
                    .toList()

            scenario("Part 1") {
                var closest = 0
                val away = Array(1000, { false })

                while (true) {
                    var ticks = 0
                    particles.forEachIndexed { index, it ->
                        if (!away[index]) {
                            it.tick()
                            ticks++

                            val cur = it.distance()
                            if (cur > 100_000) {
                                away[index] = true
                            }
                            closest = index
                        }
                    }
                    if (ticks == 1) break
                }
                println(closest)
            }

            scenario("Part 2") {
                var ps = particles.toList()

                repeat(50) {
                    ps = ps.groupBy { it.position() }
                            .values
                            .filter { it.size == 1 }
                            .map { 
                                it[0].tick()
                                Particle(it[0].p, it[0].v, it[0].a)
                            }
                }
                
                println(ps.size.toString())
            }
        }
    }
}
