package day1

fun main(args: Array<String>) {
    val seq = ClassLoader.getSystemClassLoader().getResource("input1").readText().trim()
    println(captcha(seq, 1)) // part 1
    println(captcha(seq, seq.length / 2)) // part 2
}

private fun captcha(seq: String, step: Int): Int =
        seq.filterIndexed { index, value -> value == seq[(index + step) % seq.length] }.map { Character.getNumericValue(it) }.sum()
