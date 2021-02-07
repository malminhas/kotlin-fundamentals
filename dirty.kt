fun isDirty(dirty: Int) : Boolean {
    return dirty > 30
}

fun main() {
    val dirty = arrayOf(10,20,30,40)
    for (v in dirty) {
	    println("isDirty(${v}) = ${isDirty(v)}")    
    }
}