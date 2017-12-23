import io.kotlintest.specs.FeatureSpec

class CoprocessorConflagrationTest : FeatureSpec() {

    init {
        feature("Day 23: Coprocessor Conflagration") {

            data class Operation(val inst: String, val op1: String, val op2: String? = null)

            val input = read("input23")

            fun parse(input: String): List<Operation> =
                    input.split("\n").map {
                        val spl = it.split(" ")
                        Operation(spl[0], spl[1], if (spl.size > 2) spl[2] else null)
                    }

            fun run(instructions: List<Operation>): Int {
                val state: MutableMap<String, Long> = mutableMapOf()
                ('a'..'h').forEach { state.put(it.toString(), 0L) }

                fun valueOf(op: String?): Long =
                        try {
                            op!!.toLong()
                        } catch (e: NumberFormatException) {
                            state.getOrDefault(op!!, 0L)
                        }

                var muls = 0
                var i = 0

                while (0 <= i && i < instructions.size) {
                    val it = instructions[i++]
                    when (it.inst) {
                        "set" -> state.put(it.op1, valueOf(it.op2))
                        "sub" -> state.put(it.op1, valueOf(it.op1) - valueOf(it.op2))
                        "mul" -> {
                            state.put(it.op1, valueOf(it.op1) * valueOf(it.op2))
                            muls++
                        }
                        "jnz" -> if (valueOf(it.op1) != 0L) {
                            i += valueOf(it.op2).toInt() - 1
                        }
                    }
                }

                return muls
            }

            scenario("Part 1") {
                println(run(parse(input)))
            }

            scenario("Part 2") {
                val x = 93 * 100 + 100_000
                val h = ((x..x + 17_000) step 17)
                        .map { b -> (2 until b).any { b % it == 0 } }
                        .count { it }
                println(h)
            }

        }
    }
}