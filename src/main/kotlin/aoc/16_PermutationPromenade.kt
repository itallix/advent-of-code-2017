package aoc

fun String.spin(n: Int) = drop(length - n) + take(length - n)
fun String.exchange(i: Int, j: Int): String {
    val chars = toMutableList()
    val tmp = chars[i]
    chars[i] = chars[j]
    chars[j] = tmp
    return chars.joinToString("")
}
fun String.partner(a: Char, b: Char): String = exchange(indexOf(a), indexOf(b))

fun dance(input: String, steps: List<String>, times: Int = 1): String {
    val origin = input
    var current = input
    var i = -1
    while (++i < times) {
        if (i > 0 && current == origin) {
            i = times - times % i
        }
        for (step in steps) {
            when (step[0]) {
                's' -> current = current.spin(step.substring(1).toInt())
                'x' -> {
                    val s = step.substring(1).split('/')
                    current = current.exchange(s[0].toInt(), s[1].toInt())
                }
                'p' -> {
                    val s = step.substring(1).split('/')
                    current = current.partner(s[0].single(), s[1].single())
                }
            }
        }
    }

    return current
}
