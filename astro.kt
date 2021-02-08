import java.net.URL

data class Assignment(
    val name:String,
    val craft: String
)

data class AstroResponse(
    val message:String,
    val number: Int,
    val people: List<Assignment>
)

fun main(){
    val url = "http://api.open-notify.org/astros.json"
    val astroStringResponse = URL(url).readText()
    println(astroStringResponse)
}