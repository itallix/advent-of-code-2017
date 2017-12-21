import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec

class DuetTest : FeatureSpec() {

    init {
        feature("Day 18: Duet") {

            data class Operation(val inst: String, val op1: String, val op2: String? = null)

            val input = read("input18")

            fun parse(input: String): List<Operation> =
                    input.split("\n").map {
                        val spl = it.split(" ")
                        Operation(spl[0], spl[1], if (spl.size > 2) spl[2] else null)
                    }

            fun findLastRcv(instructions: List<Operation>): Long {
                val state: MutableMap<String, Long> = mutableMapOf()

                fun valueOf(op: String?): Long =
                        try {
                            op!!.toLong()
                        } catch (e: NumberFormatException) {
                            state.getOrDefault(op!!, 0L)
                        }

                var lastSound = 0L
                var i = 0

                while (true) {
                    val it = instructions[i++]
                    when (it.inst) {
                        "set" -> state.put(it.op1, valueOf(it.op2))
                        "snd" -> lastSound = valueOf(it.op1)
                        "add" -> state.put(it.op1, valueOf(it.op1) + valueOf(it.op2))
                        "mul" -> state.put(it.op1, valueOf(it.op1) * valueOf(it.op2))
                        "mod" -> state.put(it.op1, valueOf(it.op1) % valueOf(it.op2))
                        "rcv" -> if (valueOf(it.op1) > 0) return lastSound
                        "jgz" -> if (valueOf(it.op1) > 0) {
                            i += valueOf(it.op2).toInt() - 1
                        }
                    }
                }
            }


            scenario("Part 1") {
                findLastRcv(parse("set a 1\n" +
                        "add a 2\n" +
                        "mul a a\n" +
                        "mod a 5\n" +
                        "snd a\n" +
                        "set a 0\n" +
                        "rcv a\n" +
                        "jgz a -1\n" +
                        "set a 1\n" +
                        "jgz a -2")) shouldBe 4L

                println(findLastRcv(parse(input)))
            }

            class Program(val instructions: List<Operation>, p: Long = 0L) {

                private val state: MutableMap<String, Long> = mutableMapOf("p" to p)
                val sendingQueue = mutableListOf<Long>()
                val waitingQueue = mutableListOf<Long>()
                private var cur: Int = 0
                var sent: Int = 0

                private fun valueOf(op: String?): Long =
                        try {
                            op!!.toLong()
                        } catch (e: NumberFormatException) {
                            state.getOrDefault(op!!, 0L)
                        }

                fun execute(): Long {
                    while (cur < instructions.size) {
                        val (inst, op1, op2) = instructions[cur]
                        when (inst) {
                            "snd" -> {
                                sendingQueue.add(valueOf(op1)); sent++
                            }
                            "set" -> state.put(op1, valueOf(op2))
                            "add" -> state.put(op1, valueOf(op1) + valueOf(op2))
                            "mul" -> state.put(op1, valueOf(op1) * valueOf(op2))
                            "mod" -> state.put(op1, valueOf(op1) % valueOf(op2))
                            "jgz" -> if (valueOf(op1) > 0L) {
                                cur += valueOf(op2).toInt() - 1
                            }
                            "rcv" -> {
                                if (waitingQueue.size == 0) {
                                    return -1
                                }
                                state.put(op1, waitingQueue.removeAt(0))
                            }
                        }
                        cur++
                    }
                    return 0
                }
            }

            fun sent(input: String): Int {
                val prog1 = Program(parse(input))
                val prog2 = Program(parse(input), 1)
                var p1 = 1L
                var p2 = 1L

                while (true) {
                    if (p1 != 0L) {
                        p1 = prog1.execute()
                    }
                    prog2.waitingQueue.addAll(prog1.sendingQueue)
                    prog1.sendingQueue.clear()
                    if (p2 != 0L) {
                        p2 = prog2.execute()
                    }
                    prog1.waitingQueue.addAll(prog2.sendingQueue)
                    prog2.sendingQueue.clear()

                    if (p1 < 0L && p2 < 0L && prog1.waitingQueue.size == 0 && prog2.waitingQueue.size == 0) break
                }
                return prog2.sent
            }

            scenario("Part 2") {
                sent("snd 1\n" +
                        "snd 2\n" +
                        "snd p\n" +
                        "rcv a\n" +
                        "rcv b\n" +
                        "rcv c\n" +
                        "rcv d") shouldBe 3

                println(sent(input))
            }
        }
    }
}