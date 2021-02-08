// signatures:
//inline fun <T, R : Comparable<R>> Iterable<T>.maxBy(selector: (T) -> R): T?
//inline fun <T, R : Comparable<R>> Array<out T>.maxBy(selector: (T) -> R): T?
    
fun main(){
    val nameToAge = listOf("Alice" to 42, "Bob" to 28, "Carol" to 51)
    val oldest = nameToAge.maxByOrNull { it.second }
    val youngest = nameToAge.minByOrNull({ it.second })
    println(oldest)
    println(youngest)
}