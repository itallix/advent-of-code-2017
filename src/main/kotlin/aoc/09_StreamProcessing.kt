package aoc

import java.util.*
import kotlin.collections.ArrayList

class Stream(input: String) {

    private val scores: ArrayList<Int> = ArrayList()
    private val characters: ArrayList<Char> = ArrayList()

    fun sum() = scores.sum()

    fun symbols() = characters.filter { it != '!' }.count()

    init {
        val stack = Stack<Char>()
        var score = 0
        input.forEach { symbol ->
            if (stack.isEmpty()) {
                stack.push(symbol)
                if (symbol == '{') score++
            } else {
                val cur = stack.peek()
                if (cur == '<' && symbol != '>') characters.add(symbol)
                if (cur != '!') {
                    when (symbol) {
                        '{' -> if (cur != '<') { stack.push(symbol); score++ }
                        '}' -> if (cur != '<' && stack.pop() == '{') scores.add(score--)
                        '<' -> if (cur != '<') stack.push(symbol)
                        '>' -> stack.pop()
                        '!' -> stack.push(symbol)
                    }
                } else stack.pop()
            }
        }
    }
}
