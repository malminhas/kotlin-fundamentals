/**
 *  Refactored version of classes
 *	Interfaces are templates for functions.
 *	They can be added to classes.
 */
interface WhoYouAre {
    fun whoAmI()
}

interface WhatDoYouDo {
    fun whatIdoIs()
}

/**
 *	Abstract classes are templates for classes.
 *	They can implement interface functions but cannot
 *	be created as an object directly. In order to user an 
 *  abstract class you must extend another class from it.
 *  This other class would then extend its logic and provide
 *  the ability to override open functions.
 */
abstract class Person(
    open val firstName: String,
    open val lastName: String,
    open val age: Int,
): WhoYouAre {
  	
    override fun whoAmI() {
        println("""
        My name is $firstName $lastName. 
        I am $age years old.
        """.trimIndent())
    }
    
}

/**
 *	An interface can be reused multiple times.
 *	It can be added to other classes and enums.
 *	It can also be used along side extentions. A
 *	class can have many interface implimentations, 
 *	but only extend from one class.
 */
abstract class Employee(
    override val firstName: String,
    override val lastName: String,
    override val age: Int,
	// Variables must be open to be overridden.
    open val job: String,
    val salary: String,
): Person(firstName, lastName, age), WhoYouAre, WhatDoYouDo {
    /**
     *	Calling super on a function thats been overridden
     *	calls the fuction thats been overridden from the 
     *	class thats been extended from.
     **/
    override fun whoAmI() {
        super.whoAmI()
    }
    
    /**
     *	This is not possible on implented interface functions
     *	and the compiler will complain.
     */
    override fun whatIdoIs() {
        whoAmI()
        println("""I work as a $job currently receiving $salary.""")
    }
}

/**
 *	Regular classes that extend from abstract classes
 *	that contain constructor values are required to pass
 *	those values at the time of construction.
 */
data class Teacher(
    override val firstName: String,
    override val lastName: String, 
    override val age: Int,
    val wage: Int,
): Employee(firstName, lastName, age, "Teacher", wage.toString())


data class Student(
    override val firstName: String, 
    override val lastName: String, 
    override val age: Int,
    val favouriteHobby: String = "I'm not sure you know..."
): Person(firstName, lastName, age), WhatDoYouDo {
    
   /**
    *	You can call functions on a variable in
    *	a string by wrapping it in { variable.function() }.
    *	The returned value from the function will be printed.
    */
   override fun whatIdoIs() {
       whoAmI()
       println("""
       	I'm still in school and I really like...
        ${favouriteHobby.toUpperCase()}
       """.trimIndent())
   }
}

class Parent(
    first: String, 
    last: String, 
    age: Int,
    val children: List<String>
): Person(first, last, age), WhatDoYouDo {

    /**
     *	You can even iterate over lists 
     *  and pass the print function. 
     */
    override fun whatIdoIs() {
      whoAmI()
      println("I'm the parent of")
      children.forEach(::println)
      println()
    }
}

fun main() {
    val jack = Teacher(
        firstName = "Jack",
        lastName = "Smith", 
        age = 32,
        wage = 32000
    ).whatIdoIs().also { println() }
    
    val jill = Student("Jill", "Jones", 16, "sInGiNg")
    val lilly = Student("lilly", "Jones", 19)
    listOf<Student>(jill, lilly).forEach { 
        it.whatIdoIs()
        println()
    }
    
    val mum = Parent("Alice", "Jones", 42, listOf("Jill ", "Lilly "))
    val dad = Parent("Tom", "Jones", 80, listOf("Jill ", "Lilly "))
    listOf(mum, dad, jack, jill, lilly)
    	.filterIsInstance(Parent::class.java)
        .map(Parent::whatIdoIs)
        .also { println("""
        
        Bye!!
        
        """.trimIndent()) }
}