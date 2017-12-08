package aoc

interface Expr
data class Condition(val name: String, val cnd: String, val operand: Int) : Expr
class Instruction(val reg: String, val op: String, val step: Int, val condition: Condition) : Expr

fun process(instructions: List<Instruction>): Pair<Int, Int> {
    val registers = hashMapOf<String, Int>()
    var historicalMax = 0
    instructions.forEach { i ->
        val (name, condition, operand) = i.condition
        val value = registers[name] ?: 0
        val calc = when (condition) {
            ">" -> value > operand
            ">=" -> value >= operand
            "==" -> value == operand
            "<" -> value < operand
            "<=" -> value <= operand
            "!=" -> value != operand
            else -> false
        }
        if (calc) {
            val cur = registers[i.reg] ?: 0
            val newValue = when (i.op) {
                "inc" -> cur + i.step
                "dec" -> cur - i.step
                else -> 0
            }
            registers[i.reg] = newValue
            if (newValue > historicalMax) {
                historicalMax = newValue
            }
        }
    }
    return Pair(registers.values.max()?:0, historicalMax)
}