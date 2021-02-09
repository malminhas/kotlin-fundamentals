# kotlin-fundamentals

Initial rough notes on Kotlin development after undertaking the [OReilly Kotlin fundamentals](https://learning.oreilly.com/live-training/courses/kotlin-fundamentals) course and the Google [Kotlin Bootcamp](https://developer.android.com/codelabs/kotlin-bootcamp-welcome#0).

## Table of Contents  
[Main Attractions](#main-attractions)  
[Environment](#environment)<br>
[Language Basics](#language-basics)<br>
[Worked Examples](#worked-examples)<br>
[Coding Conventions](#coding-conventions)<br>
[What you can build with Kotlin](#what-you-can-build-with-kotlin)<br>
[Frameworks](#frameworks)<br>

## Main attractions
* Built-in null safety.
* First class coroutine support.
* Discourages inheritance in favour of composition.
* Allows you to use the same language for web + mobile apps and microservices.
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
* Can extend properties or functions.  An extension function is a member function of a class that is defined outside the class.
```
fun String.removeFirstLastChar(): String =  this.substring(1, this.length - 1)

fun main() {
    val myString= "Hello Everyone"
    val result = myString.removeFirstLastChar()
    println("Result is: $result")
}
Result is: ello Everyon
```

## Worked Examples
This repository contains some more extended worked examples to showcase different aspects of Kotlin:
* [Astro](Astro): IntelliJ project that contains example of how to invoke web services APIs and deserialize JSON to a `data` class.
* [Person](Person): IntelliJ projec that illustrates how Kotlin classes work using `interface`, `open` and `override`.

## Coding Conventions
Taken from [kotlinlang.org reference](https://kotlinlang.org/docs/reference/coding-conventions.html).
### Class layout
In this order:
- Property declarations and initializer blocks
- Secondary constructors
- Method declarations
- Companion object
### Interfaces
> When implementing an interface, keep the implementing members in the same order as members of the interface 
### Naming
- Use camelCase
- 4 spaces for indentation, no tabs.
### Formatting
- Colon handling:
```
abstract class Foo<out T : Any> : IFoo {
    abstract fun foo(a: Int): T
}
```
- Prefer trailing commas:
> To enable trailing commas in the IntelliJ IDEA formatter, go to Settings | Editor | Code Style | Kotlin, open the Other tab and select the Use trailing comma option.
### Lambda handling
> If a call takes a single lambda, it should be passed outside of parentheses whenever possible.
```
list.filter { it > 10 }
```
### Scope functions
> Kotlin provides a variety of functions to execute a block of code in the context of a given object: `let`, `run`, `with`, `apply`, and `also`
> For the guidance on choosing the right scope function for your case, refer to [Scope Functions](https://kotlinlang.org/docs/reference/scope-functions.html).
> The Kotlin standard library contains several functions whose sole purpose is to execute a block of code within the context of an object. When you call such a function on an object with a lambda expression provided, it forms a temporary scope. In this scope, you can access the object without its name. Such functions are called scope functions. There are five of them.

## What you can build with Kotlin
* [Build a mobile application](https://kotlinlang.org/docs/mobile/create-first-app.html): Targeting both Android and iOS using Android Studio.
* [Build a simple web site](https://play.kotlinlang.org/hands-on/Creating%20a%20website%20with%20Ktor/01_introduction): Static site using `ktor` with templates and IntelliJ IDEA.
* [Create a full stack web app](https://play.kotlinlang.org/hands-on/Full%20Stack%20Web%20App%20with%20Kotlin%20Multiplatform/01_Introduction): Kotlin/JVM server and Kotlin/JS client using IntelliJ IDEA.  The Kotlin/JS client can [optionally be built with React](https://play.kotlinlang.org/hands-on/Building%20Web%20Applications%20with%20React%20and%20Kotlin%20JS/01_Introduction).
* [Create a RESTful microservice](https://play.kotlinlang.org/hands-on/Creating%20HTTP%20APIs%20with%20Ktor/01_introduction): Using `ktor` and IntelliJ IDEA.
* [Create a CLI tool](https://play.kotlinlang.org/hands-on/Introduction%20to%20Kotlin%20Native/01_Introduction): For example a CLI that wraps `curl`.

## Frameworks
* [Micronaut](https://micronaut.io/) - Lightweight framework for writing microservices.  Supports: Java, Kotlin, Groovy
* [SpringBoot](https://spring.io/projects/spring-boot) - Industry heavyweight framework for writing backends.  Supports: Java, Kotlin, Scala, Groovy
* [Ktor](https://ktor.io/) - Kotlin native async framework for microservices.  Can be used to build clients too.
