package duck.develop.calculator.data.source

import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.entity.Movie

/**
 * Created by Hwang on 2019-10-21.
 *
 * Description : 영화 데이터 인터페이스
 */
interface MovieDataSource {
    suspend fun getMovies(page: Int, rowPerPage: Int): Result<List<Movie>>
}