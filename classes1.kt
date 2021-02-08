abstract class Person(first: String, last: String, age: Int) {
	
    open var first: String = first
    open var last: String = last
    open var job: String? = null
    open var salary: Int = 0
    
    init {
        //println("My name is $first $last. I am $age years old.")
    }
}

class Teacher(first: String, last: String, age: Int, salary: Int): Person(first, last, age) {
    
	override var salary = salary
    
    init {
        job = "teacher"
        //println("I am a $job on £$salary a year.")
    }
    
}

class Student(first: String, last: String, age: Int): Person(first, last, age) {
	
    init {
        job = "student"
        //println("I am a $job.")
    }
    
}

class Parent(first: String, last: String, age: Int): Person(first, last, age) {

    init {
        //if (job != null) println("I am a $job.") else println("I have no job")
    }
    
}

fun main() {
    val jack = Teacher("Jack", "Smith", 32, 32000)
    val jill = Student("Jill", "Jones", 16)
    val mum = Parent("Alice", "Jones", 42)
    println("I am ${jack.first}.  I am a ${jack.job} earning ${jack.salary}")
    println("I am ${jill.first}.  I am a ${jill.job} earning ${jill.salary}")
    println("I am ${mum.first}.  I am a ${mum.job} earning ${mum.salary}")
}
    
