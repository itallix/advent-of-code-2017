package aoc

import java.util.*
import kotlin.collections.ArrayList

class Stream(input: String) {

    private val scores: ArrayList<Int>
    private val characters:  ArrayList<Char>

    fun sum() = scores.sum()

    fun symbols() = characters.filter { it != '!' }.count()

    init {
        val stack = Stack<Char>()
        this.scores = ArrayList()
        var score = 0
        this.characters = ArrayList()
        input.forEach { symbol ->
            if (stack.isEmpty()) {
                stack.push(symbol)
                if (symbol == '{') score++
            } else {
                if (stack.peek() == '<' && symbol != '>') characters.add(symbol)
                if (stack.peek() != '!') {
                    when (symbol) {
                        '{' -> {
                            if (stack.peek() != '<') {
                                stack.push(symbol)
                                score++
                            }
                        }
                        '}' -> {
                            if (stack.peek() != '<') {
                                val ss = stack.pop()
                                if (ss == '{') {
                                    scores.add(score--)
                                }
                            }
                        }
                        '<' -> {
                            if (stack.peek() != '<') {
                                stack.push(symbol)
                            }
                        }
                        '>' -> stack.pop()
                        '!' -> stack.push(symbol)
                    }
                } else stack.pop()
            }
        }
    }
}
