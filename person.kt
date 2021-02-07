
import java.time.LocalDate
import java.time.Month

data class Person(
    val first: String,
    val last: String? = null,
    val dob: LocalDate?
)

// we are using safe call with the Elvis operator to give us a ternary
fun getLength(string: String?) = string?.length ?: 0

fun main() {
    val me = Person(first="Mal", last = "Minhas", 
                   dob=LocalDate.of(1968, Month.NOVEMBER, 20))
    val spidey = Person(first="Spiderman", 
                   dob=LocalDate.of(1968, Month.NOVEMBER, 20))
    println("${me}, ${getLength(me.last)}")
    println("${spidey}, ${getLength(spidey.last)}")
}