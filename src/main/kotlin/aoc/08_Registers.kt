package aoc

interface Expr
class Condition(val name: String, val cnd: String, val operand: Int) : Expr
class Instruction(val reg: String, val op: String, val step: Int, val condition: Condition) : Expr

fun process(instructions: List<Instruction>): Pair<Int, Int> {
    val registers = hashMapOf<String, Int>()
    val history = arrayListOf<Int>()
    instructions.forEach { i ->
        val condition = i.condition
        val value = registers[condition.name] ?: 0
        val calc = when (condition.cnd) {
            ">" -> value > condition.operand
            ">=" -> value >= condition.operand
            "==" -> value == condition.operand
            "<" -> value < condition.operand
            "<=" -> value <= condition.operand
            "!=" -> value != condition.operand
            else -> false
        }
        if (calc) {
            val cur = registers[i.reg] ?: 0
            registers[i.reg] = when (i.op) {
                "inc" -> cur + i.step
                "dec" -> cur - i.step
                else -> 0
            }
            if (registers[i.reg]!! > 0) {
                history.add(registers[i.reg]!!)
            }
        }
    }
    return Pair(registers.values.max()?:0, history.max()?:0)
}