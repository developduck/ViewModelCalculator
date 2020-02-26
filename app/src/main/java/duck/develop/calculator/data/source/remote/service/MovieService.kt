package duck.develop.calculator.data.source.remote.service

import duck.develop.calculator.data.model.Root
import duck.develop.calculator.data.model.entity.Keyboard
import duck.develop.calculator.data.model.entity.Movie
import duck.develop.calculator.data.model.query.SelectKeyboardJoinKeyAll
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Hwang on 2019-07-10.
 *
 * Description : Keyboard Service 추상 객체
 */
interface MovieService {
    @GET("/test/get_movies.php")
    suspend fun getMovies(@Query("page") page: Int, @Query("row_per_page") rowPerPage: Int): Root<List<Movie>>
}