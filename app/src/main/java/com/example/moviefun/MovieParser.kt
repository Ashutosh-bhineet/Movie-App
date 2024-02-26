import android.content.Context
import com.example.moviefun.Movie
import org.json.JSONArray
import org.json.JSONException

object MovieParser {
    fun parseJson(context: Context,jsonString: String): List<Movie> {
        val movies = mutableListOf<Movie>()
        try {
            val jsonString =context.assets.open(jsonString).bufferedReader().use{
                it.readText()
            }
            val jsonArray=JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val title = jsonObject.getString("title")
                val imageName = jsonObject.getString("imageName")
                val description = jsonObject.getString("description")
                val director = jsonObject.getString("director")
                val starCastArray = jsonObject.getJSONArray("starCast")
                val imdbRaring=jsonObject.getString("imdbRating")
                val starCast = mutableListOf<String>()
                for (j in 0 until starCastArray.length()) {
                    starCast.add(starCastArray.getString(j))
                }
                val releaseDate = jsonObject.getString("releaseDate")
                movies.add(Movie(title, imageName, description, director, starCast, releaseDate,imdbRaring))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movies
    }
}
