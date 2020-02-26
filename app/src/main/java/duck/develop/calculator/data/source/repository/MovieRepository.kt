package duck.develop.calculator.data.source.repository

import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.entity.Movie
import duck.develop.calculator.data.source.MovieDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Hwang on 2019-10-21.
 *
 * Description : 영화 데이터 관련 저장소
 */
class MovieRepository(
    private val remote: MovieDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): MovieDataSource {
    override suspend fun getMovies(page: Int, rowPerPage: Int): Result<List<Movie>> =
        withContext(dispatcher) {
            return@withContext remote.getMovies(page, rowPerPage)
        }
}