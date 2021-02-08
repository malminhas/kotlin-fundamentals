import java.net.URL
import com.google.gson.Gson
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.Instant
import java.time.ZonedDateTime
import java.util.TimeZone

data class AstronautAssignment(
    val name:String,
    val craft: String
)

data class AstronautResult(
    val message:String,
    val number: Int,
    val people: List<AstronautAssignment>
)

class AstronautRequest {
    companion object {
        private const val ASTRO_URL = "http://api.open-notify.org/astros.json"
    }

    operator fun invoke(): AstronautResult =
        Gson().fromJson(URL(ASTRO_URL).readText(), AstronautResult::class.java)
}

data class IssPosition(
    val latitude: Double,
    val longitude: Double
)

data class IssResponse(
    val message: String,
    val iss_position: IssPosition,
    val timestamp: Long
) {
    val zdt: ZonedDateTime
        get() = ZonedDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            TimeZone.getDefault().toZoneId()
        )
}

class ISSRequest {
    companion object {
        private const val ISS_URL = "http://api.open-notify.org/iss-now.json"
    }

    fun invoke(): IssResponse =
        Gson().fromJson(URL(ISS_URL).readText(), IssResponse::class.java)

    fun getPosition() =
        invoke().also {
            println(
                "As of " + it.zdt.format(
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                )
            )
        }.iss_position
}

fun main(){
    val r = AstronautRequest()
    val response = r.invoke()
    println(response)
    println("Found ${response.number} astronauts in Space:")
    println("-------------------------")
    for ((name,craft) in response.people) {
        println("$name on $craft")
    }
    println("-------------------------")
    val r2 = ISSRequest()
    val response2 = r2.getPosition()
    println(response2)
}