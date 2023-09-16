package quickstart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.citizencardcontrollermobileapp.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.http4k.client.Java8HttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request


@JsonClass(generateAdapter = true)
data class Response(
    val id: Int,
    val answer: String,
    val question: String
)

fun main() {

    val request = Request(Method.GET, "http://jservice.io/api/random?count=1")
    val client: HttpHandler = Java8HttpClient()
    val data = client(request).body
    println(data)
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, Response::class.java)
    val jsonAdapter:JsonAdapter<List<Response>> = moshi.adapter(type)
    val r: List<Response>? = jsonAdapter.fromJson(data.toString())
    print(r)

}



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}