# kotlin-fundamentals

Initial rough notes on Kotlin development after undertaking OReilly Kotlin fundamentals and Kotlin Bootcamp.

## Main attractions
* Built-in null safety.
* First class coroutine support.
* Discourages inheritance in favour of composition.
* JetBrains are really pushing multiplatform Kotlin.

## Environment
* Statically typed compiled language.  Compiled to Java byte code.
* Handy online [playground](https://play.kotlinlang.org/) which you can access on your phone.
* Needs JVM.  Make sure you have Java 1.8 ideally installed:
```
$ brew install java
$ java -version
openjdk version "1.8.0_222"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_222-b10)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.222-b10, mixed mode)
$ brew install kotlin
$ kotlin -version
Kotlin version 1.4.21-release-351 (JRE 15.0.1+9)
```
* IntelliJ IDEA comes in two flavours - Community (free) and Ultimate (paid).
* Android Studio for Android dev.
* IntelliJ IDEA and Android Studio are integrated with Gradle build tooling.
* Can just use CLI as follows to compile and run a Kotlin self-contained CLI file `foo.kt`:
```
$ kotlinc foo.kt -include-runtime -d foo.jar
$ java -jar foo.jar
```
* `buildit` script in this repo will compile to .jar and run as follows on any Kotlin file foo.kt:
```
$ ./buildit foo
```
* REPL also available from CLI as follows:
```
$ kotlin
Welcome to Kotlin version 1.4.21 (JRE 15.0.1+9)
Type :help for help, :quit for quit
>>>
```
* Finally, there is support for a Kotlin kernel in Jupyter.

## Language Basics
* Hello World:
```
fun main(){
    print("Hello, World!")
}
```
* In Kotlin almost everything is an expression which has a value even if that value is `Unit` (equivalent to void).
* Two types of variable `val` and `var`.   Use `val` wherever possible and `var` only where necessary.
* String interpolation.  `"I have ${a+b} things"`
* Lists, Arrays, HashMaps.  `listOf()`,`arrayOf()` and `mapOf()`:
```
fun main() {
    val map = mapOf(Pair("a",1), "b".to(2), "c" to 3)
    for ((k, v) in map) {
        println("$k = $v")
    }
}
a = 1
b = 2
c = 3
```
* `when` is the Kotlin equivalent of `switch`.  Must be exhaustive:
``` 
when (n) {
    0 -> println("empty")
    in 1..39 -> println("plenty of fish")
    else -> println("lots of fish!")
}
```
* `for` loop can be used to enumerate items:
```
fun main() {
    val arr = intArrayOf(3,1,4,5,9)
    for ((index,element) in arr.withIndex()){
        println("element $index has value $element")
    }
}
element 0 has value 3
element 1 has value 1
element 2 has value 4
element 3 has value 5
element 4 has value 9
```
* `if` statement returns a value allow it to be used to construct ternary expressions.
* Single expression function can be used with Elvis operator `?:` to build a ternary operator to check for `null`:
```
fun getLength(string: String?) = string?.length ?: 0
```
* Can extend properties or functions.
* `===` tests reference equality of object.
* Kotlin has support for classes
* `data` class is similar to struct.  Comes with a few extra benefits
```
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
    val spidey = Person(first="Spiderman", dob=LocalDate.of(1983, Month.NOVEMBER, 10))
    println("${spidey}, ${getLength(spidey.last)}")
}
Person(first=Spiderman, last=null, dob=1983-11-10), 0
```
* Kotlin has support for generic types:
```
import kotlin.test.*

class PClass<A>(private val value: A) {
    fun getValue(): A {
        return value
    }
}

fun main() {
    val p1 = PClass<String>("I am a string")
    val r1 = p1.getValue()
    assertTrue(r1 is String)
    val p2 = PClass<Int>(32)
    val r2 = p2.getValue()
    assertTrue(r2 is Int)
}
```
* Annotations for attaching metadata to code.

## Frameworks
* [Micronaut](https://micronaut.io/) - Lightweight framework for writing microservices.  Supports: Java, Kotlin, Groovy
* [SpringBoot](https://spring.io/projects/spring-boot) - Industry heavyweight framework for writing backends.  Supports: Java, Kotlin, Scala, Groovy
* [Ktor](https://ktor.io/) - Kotlin native async framework for microservices.  Can be used to build clients too.
