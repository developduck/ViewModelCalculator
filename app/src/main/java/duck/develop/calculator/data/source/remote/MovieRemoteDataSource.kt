package duck.develop.calculator.data.source.remote

import duck.develop.calculator.data.model.Result
import duck.develop.calculator.data.model.entity.Movie
import duck.develop.calculator.data.source.MovieDataSource
import duck.develop.calculator.data.source.remote.service.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.NullPointerException

class MovieRemoteDataSource(
    private val service: MovieService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): MovieDataSource {
    override suspend fun getMovies(page: Int, rowPerPage: Int): Result<List<Movie>> =
        withContext(dispatcher) {
            return@withContext try {
                service.getMovies(page, rowPerPage).data?.let {
                    Result.Success(it)
                } ?: throw NullPointerException()
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}