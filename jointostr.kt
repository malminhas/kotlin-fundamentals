//import kotlin.test.*

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    println(numbers.joinToString()) // 1, 2, 3, 4, 5, 6
    println(numbers.joinToString(prefix = "[", postfix = "]")) // [1, 2, 3, 4, 5, 6]
    println(numbers.joinToString(prefix = "<", postfix = ">", separator = "•")) // <1•2•3•4•5•6>

    val chars = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q')
    println(chars.joinToString(limit = 5, truncated = "...!") { it.toUpperCase().toString() }) // A, B, C, D, E, ...!
}